package com.example.todo.infrastructure.dao

import com.example.todo.infrastructure.record.UserRecord
import org.seasar.doma.Dao
import org.seasar.doma.Insert
import org.seasar.doma.Update
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.Result

@ConfigAutowireable
@Dao
interface UserDao {

  @Insert
  fun insert(user: UserRecord): Result<UserRecord>

  @Update
  fun update(user: UserRecord): Result<UserRecord>
}
