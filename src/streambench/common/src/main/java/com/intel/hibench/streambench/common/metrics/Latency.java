/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.hibench.streambench.common.metrics;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

public class Latency {
    private final Histogram histogram;
    private final MetricRegistry registry;
    private final int sampleRate;
    private long sampleCount = 0L;

    public Latency(String name) {
        this(name, 1);
    }

    public Latency(String name, int sampleRate) {
        this.registry = new MetricRegistry();
        this.histogram = registry.histogram(name);
        this.sampleRate = sampleRate;
    }

    public void update(long value) {
        sampleCount++;
        if (sampleCount % sampleRate == 0) {
            histogram.update(value);
        }
    }

    public MetricRegistry getRegistry() {
        return registry;
    }

}
