# Tech Radar

## Запуск приложения
Основное требование — наличие установленного Docker на вашем компьютере. Если он не установлен, вы можете установить его здесь: https://docs.docker.com/get-docker/

Далее склонируйте репозиторий на свой компьютер:

```
git clone https://github.com/Shimady563/t1-debut-team5.git
```

Перейдите в папку со склонированным проектом и запустите проект с помощью docker compose

```
cd t1-debut-team5
docker compose -f local-compose.yaml up -d
```

После запуска приложение будет доступно по адресу: http://localhost:5173

Документация swagger для API доступна по адресу: http://localhost:8080/api/v1/swagger-ui/index.html

Чтобы остановить приложение, выполните команду:

```
docker compose -f local-compose.yaml down
```