
# Clean Me Up Rest

* Logs are kept in a super secure server that is fully GDPR compliant.

## Running

* VALID: curl http://localhost:8080/email/send -H "Authorization: Basic dGVtcDp0ZW1w" -H "Content-Type: application/json" --data "{ \"address\": \"jo@test.se\", \"subject\": \"lala\", \"content\": \"asd\n\nhej\" }" -D -
* Forbidden: curl http://localhost:8080/email/send -H "Authorization: Basic bm86bm8=" -H "Content-Type: application/json" --data "{ \"address\": \"jo@test.se\", \"subject\": \"lala\", \"content\": \"asd\n\nhej\", \"test\": \"hmm\" }" -D -
* Unauthorized: curl http://localhost:8080/email/send -H "Content-Type: application/json" --data "{ \"address\": \"jo@test.se\", \"subject\": \"lala\", \"content\": \"asd\n\nhej\", \"test\": \"hmm\" }" -D -

## Documentation

* http://localhost:8080/v3/api-docs/
* http://localhost:8080/swagger-ui.html

## Versions

* 0.1.0: Cleanup and API changes
* 0.0.1: Initial

## TODO:
* Add unit tests (ongoing)
* Extract interface(s)
* Validate/sanity check input (started)
    * Did I see something about a max length for content?
* Secrets in external storage (like Vault)
* Domain object to wrap bad legacy SmtpEmail (could probably do better, builder perhaps?)
* Create docker-compose for dev to enable easy usage of depending systems
* Change to Oauth2 in WebSecurityConfig

* Add Prometheus metrics to enable monitoring
* Add Splunk/Kibana logging, with json log formatter
* Add Gitlab/Github pipeline config

* With a more complex functionality Cucumber tests might be suitable

* Would this application hold up under load?
