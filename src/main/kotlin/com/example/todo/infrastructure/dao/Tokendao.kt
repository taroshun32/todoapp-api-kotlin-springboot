package com.example.todo.infrastructure.dao

import com.example.todo.infrastructure.record.TokenRecord
import org.seasar.doma.Dao
import org.seasar.doma.Insert
import org.seasar.doma.Update
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.Result

@ConfigAutowireable
@Dao
interface TokenDao {

  @Insert
  fun store(user: TokenRecord): Result<TokenRecord>

  @Update
  fun update(user: TokenRecord): Result<TokenRecord>
}
