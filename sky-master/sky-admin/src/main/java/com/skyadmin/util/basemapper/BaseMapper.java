package com.skyadmin.util.basemapper;

import java.util.List;

/**
 * @author sky
 * @date 2022/2/6 15:26
 */
public interface BaseMapper<D, E> {
    /**
     * DTO转Entity
     *
     * @param dto
     * @return
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     *
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     *
     * @param dtoList
     * @return
     */
    List<E> toEntityList(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     *
     * @param entityList
     * @return
     */
    List<D> toDtoList(List<E> entityList);
}
