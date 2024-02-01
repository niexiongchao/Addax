/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.wgzhao.addax.common.element;

import java.util.Map;

/**
 * Created by jingxing on 14-8-24.
 */

public interface Record
{
    void addColumn(Column column);

    void setColumn(int i, final Column column);

    Column getColumn(int i);

    @Override
    String toString();

    int getColumnNumber();

    int getByteSize();

    int getMemorySize();

    public void setMeta(Map<String, String> meta);

    public Map<String, String> getMeta();
}
