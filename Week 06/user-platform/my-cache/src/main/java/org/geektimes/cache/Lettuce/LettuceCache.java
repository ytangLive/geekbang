package org.geektimes.cache.Lettuce;

import io.lettuce.core.api.sync.RedisCommands;
import org.geektimes.cache.AbstractCache;
import org.geektimes.cache.util.ObjectSerialize;

import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.util.Iterator;

public class LettuceCache<K extends Serializable, V extends Serializable> extends AbstractCache<K, V> {

    private final RedisCommands syncCommands;

    protected LettuceCache(CacheManager cacheManager, String cacheName, Configuration<K, V> configuration, RedisCommands commands) {
        super(cacheManager, cacheName, configuration);
        this.syncCommands = commands;
    }

    @Override
    protected V doGet(K key) throws CacheException, ClassCastException {
        Object valueBytes = syncCommands.get(key);
        return (V) valueBytes;
    }

    @Override
    protected V doPut(K key, V value) throws CacheException, ClassCastException {
        Object oldValue = syncCommands.get(key);
        syncCommands.set(key,value) ;
        return (V)oldValue;
    }

    @Override
    protected V doRemove(K key) throws CacheException, ClassCastException {
        Object oldValue = syncCommands.get(key);
        syncCommands.del(key);
        return (V)oldValue;
    }

    @Override
    protected void doClear() throws CacheException {

    }

    @Override
    protected Iterator<Entry<K, V>> newIterator() {
        return null;
    }
}
