# Тестовое задание для JavaCode

## Эндпоинты

POST api/v1/wallet
```json
{
  "valletId": "UUID",
  "operationType": "DEPOSIT or WITHDRAW",
  "amount": 1000
}
```
 - Изменяет баланс кошелька.

GET api/v1/wallets/{WALLET_UUID}
 - Возвращает баланс кошелька

GET api/v1/wallets
 - Возвращает все кошельки

## Запуск приложения

```bash
#run
docker compose up --build
```
