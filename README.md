# Reward-Points App
Run the application as spring boot app
# Rest End Points: 
POST: http://localhost:8282/RetailRewards/api/rewards
# Sample Json Request:
{
    "timePeriod": 3,
    "transactions": [
        {
            "amount": "120",
            "transactionDate": "2022-09-02",
            "customerid": "1111"
        },
        {
            "amount": "150",
            "transactionDate": "2022-10-02",
            "customerid": "1111"
        },
        {
            "amount": "160",
            "transactionDate": "2022-11-02",
            "customerid": "1111"
        },
        {
            "amount": "120",
            "transactionDate": "2022-09-02",
            "customerid": "2222"
        }
    ]
}


