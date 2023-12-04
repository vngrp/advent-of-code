fun <K, V> Map<K, V>.mutate(block: (mutableMap: MutableMap<K, V>) -> Map<K, V>): Map<K, V> = toMutableMap().let { block(it) }
