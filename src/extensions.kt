fun <K, V> Map<K, V>.put(index: K, block: Map<K, V>.(currentValue: V?) -> V): Map<K, V> = toMutableMap().apply { put(index, block(get(index))) }
