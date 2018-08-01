@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7')
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON
import groovy.json.JsonSlurper

url = "http://nexus/service/rest/v1/script"
def authSite = new RESTClient(url)
authSite.setHeaders([Authorization: "Basic YWRtaW46YWRtaW4xMjM="])

def newReposit = new JsonSlurper().parseText('{   "name": "Create_Repo","type": "groovy", "content": "repository.createMavenHosted(\'repo2\')" }')
def resp = authSite.post(
        contentType: JSON,
        body: newReposit,
        headers: [Accept: 'application/json'])
println("Status for newReposit: " + resp.status)

def newUser = new JsonSlurper().parseText('{"name": "Create_User", "content": "security.addUser(\'user\', \'user\', \'user\', \'user@users.kg\', true, \'user\', [\'nx-admin\'])","type": "groovy" }')
def resp1 = authSite.post(
        contentType: JSON,
        body: newUser,
        headers: [Accept: 'application/json'])
println("Status for newUser: " + resp1.status)

def updatePass = new JsonSlurper().parseText('{ "name": "Change_Password", "content": "security.securitySystem.changePassword(\'user\',\'admin123\')", "type": "groovy"}')
def resp2 = authSite.post(
        contentType: JSON,
        body: updatePass,
        headers: [Accept: 'application/json'])
println("Status for updatePass: " + resp2.status)

def dellUser = new JsonSlurper().parseText('{"name": "DeleteUser", "content": "security.securitySystem.deleteUser(\'user\')", "type": "groovy"}')
def resp3 = authSite.post(
        contentType: JSON,
        body: dellUser,
        headers: [Accept: 'application/json'])
println("Status for dellUser: " + resp3.status)


