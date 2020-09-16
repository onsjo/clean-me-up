
# Clean Me Up Rest

* Logs are kept in a super secure server that is fully GDPR compliant.

## Running

* curl http://localhost:8080/email/send -H "Authorization: Basic dGVtcDp0ZW1w" -H "Content-Type: application/json" --data "{ \"address\": \"jo@test.se\", \"subject\": \"lala\", \"content\": \"asd\n\nhej\" }" -D -

## Documentation

* http://localhost:8080/v3/api-docs/
* http://localhost:8080/swagger-ui.html

## TODO:
* Add unit tests (ongoing)
* Extract interface(s)
* Validate/sanity check input (started)
* Secrets in external storage (like Vault)
* Domain object to wrap bad legacy SmtpEmail (could probably do better, builder perhaps?)
* Create dockerfile
* Change to Oauth2 in WebSecurityConfig
