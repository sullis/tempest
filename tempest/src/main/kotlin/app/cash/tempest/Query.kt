/*
 * Copyright 2020 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.cash.tempest

import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity.NONE

interface Queryable<K : Any, I : Any> {

  /**
   * Reads up to the [pageSize] items or a maximum of 1 MB of data. This limit applies before the
   * filter expression is evaluated.
   */
  fun query(
    keyCondition: KeyCondition<K>,
    consistentRead: Boolean = false,
    asc: Boolean = true,
    pageSize: Int = 100,
    returnConsumedCapacity: ReturnConsumedCapacity = NONE,
    filterExpression: FilterExpression? = null,
    initialOffset: Offset<K>? = null
  ): Page<K, I>
}

/**
 * Used to query a table or an index.
 */
sealed class KeyCondition<K : Any>

/**
 * Applies equality condition on the hash key and the following condition on the range key
 * - begins_with (a, substr)— true if the value of attribute a begins with a particular substring.
 */
data class BeginsWith<K : Any>(
  val prefix: K
) : KeyCondition<K>()

/**
 * Applies equality condition on the hash key and the following condition on the range key
 * - a BETWEEN b AND c — true if a is greater than or equal to b, and less than or equal to c.
 */
data class Between<K : Any>(
  val startInclusive: K,
  val endInclusive: K
) : KeyCondition<K>()
