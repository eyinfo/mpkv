package com.eyinfo.mpkv;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;

import com.tencent.mmkv.MMKV;

import java.util.Set;

public class MPLocalKV {

    private static volatile MPLocalKV instance;

    public static MPLocalKV getInstance() {
        if (instance == null) {
            synchronized (MPLocalKV.class) {
                if (instance == null) {
                    instance = new MPLocalKV();
                }
            }
        }
        return instance;
    }

    private MMKV _mmkv;

    /**
     * 初始化本地存储库
     *
     * @param context Context
     */
    public void init(Context context) {
        MMKV.initialize(context);
    }

    private MMKV getMmkv() {
        if (_mmkv == null) {
            _mmkv = MMKV.defaultMMKV();
        }
        return _mmkv;
    }

    /**
     * 存储Set
     *
     * @param key                    String
     * @param value                  Set<String>
     * @param expireDurationInSecond 过期时间，单位秒
     * @return true:存储成功，false:存储失败
     */
    public boolean putSet(String key, Set<String> value, int expireDurationInSecond) {
        if (TextUtils.isEmpty(key) || value == null) {
            return false;
        }
        MMKV mmkv = getMmkv();
        if (expireDurationInSecond > 0) {
            return mmkv.encode(key, value, expireDurationInSecond);
        } else {
            return mmkv.encode(key, value);
        }
    }

    /**
     * 存储Set
     *
     * @param key   String
     * @param value Set<String>
     * @return true:存储成功，false:存储失败
     */
    public boolean putSet(String key, Set<String> value) {
        return putSet(key, value, 0);
    }

    /**
     * 存储其他类型
     *
     * @param key                    String
     * @param value                  T
     * @param expireDurationInSecond 过期时间，单位秒
     * @param <T>
     * @return true:存储成功，false:存储失败
     */
    public <T> boolean put(String key, T value, int expireDurationInSecond) {
        if (TextUtils.isEmpty(key) || value == null) {
            return false;
        }
        MMKV mmkv = getMmkv();
        if (value instanceof String) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (String) value, expireDurationInSecond) : mmkv.encode(key, (String) value);
        } else if (value instanceof Integer) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (Integer) value, expireDurationInSecond) : mmkv.encode(key, (Integer) value);
        } else if (value instanceof Boolean) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (Boolean) value, expireDurationInSecond) : mmkv.encode(key, (Boolean) value);
        } else if (value instanceof Long) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (Long) value, expireDurationInSecond) : mmkv.encode(key, (Long) value);
        } else if (value instanceof Double) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (Double) value, expireDurationInSecond) : mmkv.encode(key, (Double) value);
        } else if (value instanceof Float) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (Float) value, expireDurationInSecond) : mmkv.encode(key, (Float) value);
        } else if (value instanceof byte[]) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (byte[]) value, expireDurationInSecond) : mmkv.encode(key, (byte[]) value);
        } else if (value instanceof Parcelable) {
            return expireDurationInSecond > 0 ? mmkv.encode(key, (Parcelable) value, expireDurationInSecond) : mmkv.encode(key, (Parcelable) value);
        }
        return false;
    }

    /**
     * 存储其他类型
     *
     * @param key   String
     * @param value T
     * @param <T>
     * @return true:存储成功，false:存储失败
     */
    public <T> boolean put(String key, T value) {
        return put(key, value, 0);
    }

    /**
     * 获取boolean
     *
     * @param key          String
     * @param defaultValue true / false
     * @return boolean
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.getBoolean(key, defaultValue);
    }

    /**
     * 获取String
     *
     * @param key          String
     * @param defaultValue 默认值
     * @return String
     */
    public String getString(String key, String defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.getString(key, defaultValue);
    }

    /**
     * 获取int
     *
     * @param key          String
     * @param defaultValue 默认值
     * @return int
     */
    public int getInt(String key, int defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.getInt(key, defaultValue);
    }

    /**
     * 获取long
     *
     * @param key          String
     * @param defaultValue 默认值
     * @return long
     */
    public long getLong(String key, long defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.getLong(key, defaultValue);
    }

    /**
     * 获取float
     *
     * @param key          String
     * @param defaultValue 默认值
     * @return float
     */
    public float getFloat(String key, float defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.getFloat(key, defaultValue);
    }

    /**
     * 获取Set
     *
     * @param key          String
     * @param defaultValue 默认值
     * @return Set<String>
     */
    public Set<String> getSet(String key, Set<String> defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.getStringSet(key, defaultValue);
    }

    /**
     * 获取Parcelable
     *
     * @param key          String
     * @param defaultValue 默认值
     * @return Parcelable
     */
    public Parcelable getParcelable(String key, Parcelable defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.decodeParcelable(key, defaultValue.getClass());
    }

    /**
     * 获取byte[]
     *
     * @param key          String
     * @param defaultValue 默认值
     * @return byte[]
     */
    public byte[] getBytes(String key, byte[] defaultValue) {
        MMKV mmkv = getMmkv();
        return mmkv.getBytes(key, defaultValue);
    }

    /**
     * 获取缓存区的实际大小
     *
     * @return long型，缓存实际大小
     */
    public long actualSize() {
        MMKV mmkv = getMmkv();
        return mmkv.actualSize();
    }

    /**
     * 判断是否存在key
     *
     * @param key String
     * @return true:存在，false:不存在
     */
    public boolean containsKey(String key) {
        MMKV mmkv = getMmkv();
        return mmkv.containsKey(key);
    }

    /**
     * 获取key对应的value的大小
     *
     * @param key String
     * @return int, 返回指定value数据的实际大小
     */
    public int getValueActualSize(String key) {
        MMKV mmkv = getMmkv();
        return mmkv.getValueActualSize(key);
    }

    /**
     * 获取缓存区的总大小
     *
     * @return long型，缓存总大小
     */
    public long totalSize() {
        MMKV mmkv = getMmkv();
        return mmkv.totalSize();
    }

    /**
     * 删除指定key的数据
     *
     * @param key String
     */
    public void remove(String key) {
        MMKV mmkv = getMmkv();
        mmkv.remove(key);
    }

    /**
     * 清空所有数据
     */
    public void clear() {
        MMKV mmkv = getMmkv();
        mmkv.clearAll();
    }
}
