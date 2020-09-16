
# Clean Me Up Rest

* Logs are kept in a super secure server that is fully GDPR compliant.

## Testing

* curl http://localhost:8080/ -H "Authorization: Basic dGVtcDp0ZW1w" -H "Content-Type: application/json" --data "{ \"address\": \"jo@test.se\", \"subject\": \"lala\", \"content\": \"asd\n\nhej\" }" -D -

## TODO:
* Add unit tests (ongoing)
* Extract interface(s)
* Validate/sanity check input (started)
* Secrets in external storage (like Vault)
* Domain object to wrap bad legacy SmtpEmail (could probably do better, builder perhaps?)
* API documentation generation
* Create dockerfile
* Change to Oauth2 in WebSecurityConfig
