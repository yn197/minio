# minio
学习分布式存储系统，例如minio以及数据同步等

## 1.安装minio

**双击start.bat脚本**

```
set MINIO_ROOT_USER=admin

set MINIO_ROOT_PASSWORD=admin123

minio.exe server   --console-address "127.0.0.1:9000"  --address "127.0.0.1:9090"   F:\date1 F:\date2 F:\date3 F:\date4
```

## 2.**下载MinIO Mc客户端** 

```
wget http://dl.minio.org.cn/client/mc/release/linux-amd64/mc
```

## 3.**赋予Mc权限** (windows可以忽略)

```
chmod +x mc
mc --help
```

## 4.添加Minio服务的访问权限（源端执行）

```
#方法1
mc config host add m1 http://127.0.0.1:9000 admin admin123
mc config host add m2 http://127.0.0.1:9001 admin admin123

#方法2
mc alias set m1 http://127.0.0.1:9000 admin admin123
mc alias set m2 http://127.0.0.1:9001 admin admin123

```

## 5.数据同步

```sh
mc mirror --remove --overwrite --watch  m1  m2
mc mirror --remove --overwrite --watch  m1:test  m2:test
```



