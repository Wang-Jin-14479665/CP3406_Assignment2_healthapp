package com.example.healthapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SportDao {

    // 查询所有 Sport（返回 Flow 方便自动监听）
    @Query("SELECT * FROM sports")
    fun getAllSports(): Flow<List<SportEntity>>

    // 插入 Sport
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSport(sportEntity: SportEntity)

    // 删除 Sport
    @Delete
    suspend fun deleteSport(sportEntity: SportEntity)
}
