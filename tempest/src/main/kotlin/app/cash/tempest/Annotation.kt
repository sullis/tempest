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

/**
 * Maps an item class property to one or more attributes in a DynamoDB table.
 *
 * If this mapped to a primary range key, it must have a prefix.
 */
annotation class Attribute(
  val name: String = "",
  val names: Array<String> = [],
  val prefix: String = ""
)

/**
 * Maps an key class to a global or local secondary index in a DynamoDB table.
 */
annotation class ForIndex(
  val name: String = ""
)
