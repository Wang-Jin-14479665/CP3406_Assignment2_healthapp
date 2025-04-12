package com.example.healthapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthTipDao {

    // 查询所有健康小提示
    @Query("SELECT * FROM health_tip")
    fun getAllTips(): Flow<List<HealthTipEntity>>

    // 插入健康小提示（批量插入，替换旧数据）
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tips: List<HealthTipEntity>)
}
