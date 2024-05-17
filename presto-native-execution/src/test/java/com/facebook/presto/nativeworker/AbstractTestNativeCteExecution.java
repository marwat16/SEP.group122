/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.nativeworker;

import com.facebook.presto.hive.TestCteExecution;
import com.facebook.presto.testing.ExpectedQueryRunner;
import com.facebook.presto.testing.QueryRunner;
import com.facebook.presto.tests.AbstractTestQueryFramework;
import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.facebook.presto.nativeworker.NativeQueryRunnerUtils.*;


public abstract class AbstractTestNativeCteExecution
        extends TestCteExecution
{
    @Override
    protected void createTables()
    {

        QueryRunner queryRunner = (QueryRunner) getExpectedQueryRunner();
        createAllTables(queryRunner);
    }

    @Override
    @Test(enabled = false)
    // Char type is not supported in Prestissimo.
    public void testPersistentCteWithChar() {}

    @Override
    // Unsupported nested encoding in Velox Parquet
    // Error : VeloxRuntimeError: vec.valueVector() == nullptr || vec.wrappedVector()->isFlatEncoding()
    // An unsupported nested encoding was found. Operator: TableWrite(1)
    @Test(enabled = false)
    public void testPersistentCteWithStructTypes() {}

    @Override
    // Unsupported nested encoding in Velox Parquet
    // Error : VeloxRuntimeError: vec.valueVector() == nullptr || vec.wrappedVector()->isFlatEncoding()
    // An unsupported nested encoding was found. Operator: TableWrite(1)
    @Test(enabled = false)
    public void testPersistentCteWithMap() {}

    @Override
    // Unsupported nested encoding in Velox Parquet
    // Error : VeloxRuntimeError: vec.valueVector() == nullptr || vec.wrappedVector()->isFlatEncoding()
    // An unsupported nested encoding was found. Operator: TableWrite(1)
    @Test(enabled = false)
    public void testPersistentCteWithArrayWhereInnerTypeIsNotSupported() {}

    @Override
    // Unsupported nested encoding in Velox Parquet
    // Error : VeloxRuntimeError: vec.valueVector() == nullptr || vec.wrappedVector()->isFlatEncoding()
    // An unsupported nested encoding was found. Operator: TableWrite(1)
    @Test(enabled = false)
    public void testPersistentCteWithArrayWhereInnerTypeSupported() {}
}
