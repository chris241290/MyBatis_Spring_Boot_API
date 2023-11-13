package com.example.springMyBatis.mybatis;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface RecordMapper {
    /**
     * Get a single record from the database based on the record
     * identifier.
     *
     * @param id record identifier.
     * @return a record object.
     */
    @Select("SELECT * FROM record WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "releaseDate", column = "release_date"),
            @Result(property = "artistId", column = "artist_id"),
            @Result(property = "labelId", column = "label_id")
    })
    Record getRecord(Long id);
}