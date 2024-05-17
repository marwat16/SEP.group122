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

import com.facebook.presto.Session;
import com.facebook.presto.testing.ExpectedQueryRunner;
import com.facebook.presto.testing.QueryRunner;
import org.testng.annotations.Test;

import static com.facebook.presto.SystemSessionProperties.*;

@Test(groups = {"parquet"})
public class TestPrestoNativeCteExecutionParquet
        extends AbstractTestNativeCteExecution
{
    @Override
    protected QueryRunner createQueryRunner()
            throws Exception
    {
        return PrestoNativeQueryRunnerUtils.createNativeCteQueryRunner(true, "DWRF");
    }

    @Override
    protected ExpectedQueryRunner createExpectedQueryRunner()
            throws Exception
    {
        return PrestoNativeQueryRunnerUtils.createJavaCteQueryRunner("DWRF");
    }

    @Override
    protected Session getSession()
    {
        return Session.builder(super.getSession())
                .setSystemProperty(PUSHDOWN_SUBFIELDS_ENABLED, "true")
                .setSystemProperty(CTE_MATERIALIZATION_STRATEGY, "NONE")
                .setCatalogSessionProperty("hive", "parquet_pushdown_filter_enabled", "true")
                .setCatalogSessionProperty("hive", "orc_compression_codec", "ZSTD")
                .setCatalogSessionProperty("hive", "temporary_table_compression_codec", "GZIP")
                .build();
    }
    @Override
    protected Session getMaterializedSession()
    {
        return Session.builder(super.getSession())
                .setSystemProperty(PUSHDOWN_SUBFIELDS_ENABLED, "true")
                .setSystemProperty(CTE_MATERIALIZATION_STRATEGY, "ALL")
                .setSystemProperty(CTE_FILTER_AND_PROJECTION_PUSHDOWN_ENABLED, "true")
                .setCatalogSessionProperty("hive", "parquet_pushdown_filter_enabled", "true")
                .setCatalogSessionProperty("hive", "orc_compression_codec", "ZSTD")
                .setCatalogSessionProperty("hive", "temporary_table_compression_codec", "GZIP")
                .build();
    }
}
