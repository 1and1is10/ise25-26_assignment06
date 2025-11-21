package de.seuhd.campuscoffee.data.impl;

import de.seuhd.campuscoffee.data.mapper.PosEntityMapper;
import de.seuhd.campuscoffee.data.persistence.PosEntity;
import de.seuhd.campuscoffee.data.persistence.PosRepository;
import de.seuhd.campuscoffee.data.util.ConstraintViolationChecker;
import de.seuhd.campuscoffee.domain.model.Pos;
import de.seuhd.campuscoffee.domain.exceptions.DuplicationException;
import de.seuhd.campuscoffee.domain.exceptions.NotFoundException;
import de.seuhd.campuscoffee.domain.ports.PosDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the POS data service that the domain layer provides as a port.
 * This layer is responsible for data access and persistence.
 * Business logic should be in the service layer.
 */
@Service
@RequiredArgsConstructor
class PosDataServiceImpl implements PosDataService {
    private final PosRepository posRepository;
    private final PosEntityMapper posEntityMapper;

    @Override
    public void clear() {
        posRepository.deleteAllInBatch();
        posRepository.flush();
        posRepository.resetSequence(); // ensure consistent IDs after clearing (for local testing)
    }

    @Override
    public @NonNull List<Pos> getAll() {
        return posRepository.findAll().stream()
                .map(posEntityMapper::fromEntity)
                .toList();
    }

    @Override
    public @NonNull Pos getByName(@NonNull String name) {
        return posRepository.findByName(name)
                .map(posEntityMapper::fromEntity)
                .orElseThrow(() -> new NotFoundException(Pos.class, PosEntity.NAME_COLUMN, name));
    }

    @Override
    public @NonNull Pos getById(@NonNull Long id) {
        return posRepository.findById(id)
                .map(posEntityMapper::fromEntity)
                .orElseThrow(() -> new NotFoundException(Pos.class, id));
    }

    @Override
    public @NonNull Pos upsert(@NonNull Pos pos) {
        // map POS domain object to entity and save
        try {
            if (pos.id() == null) {
                // create new POS
                return posEntityMapper.fromEntity(
                        posRepository.saveAndFlush(posEntityMapper.toEntity(pos))
                );
            }

            // update existing POS
            PosEntity posEntity = posRepository.findById(pos.id())
                    .orElseThrow(() -> new NotFoundException(Pos.class, pos.id()));

            // use mapper to update entity fields automatically
            // note: timestamps are managed by JPA lifecycle callbacks (@PreUpdate)
            posEntityMapper.updateEntity(pos, posEntity);

            return posEntityMapper.fromEntity(posRepository.saveAndFlush(posEntity));
        } catch (DataIntegrityViolationException e) {
            // translate database constraint violations to domain exceptions
            // this is the adapter's responsibility in hexagonal architecture
            if (ConstraintViolationChecker.isConstraintViolation(e, PosEntity.NAME_CONSTRAINT)) {
                throw new DuplicationException(Pos.class, PosEntity.NAME_COLUMN, pos.name());
            }
            // re-throw if it's a different constraint violation
            throw e;
        }
    }

    @Override
    public void delete(@NonNull Long id) {
        if (!posRepository.existsById(id)) {
            throw new NotFoundException(Pos.class, id);
        }
        posRepository.deleteById(id);
    }
}
