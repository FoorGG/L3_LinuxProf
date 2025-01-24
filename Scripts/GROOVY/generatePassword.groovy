import java.security.SecureRandom
import groovy.transform.Field
import groovy.json.JsonOutput

// Определение переменных
String user_name = "otus_admin"
String user_id = generatePassword(5)
String passwd = generatePassword(25)
String user = user_name + "-" + user_id

// Создание объекта для хранения данных
def userData = [
        username: user,
        password: passwd
]

// Запись данных в текстовый файл
def pswdFile = new File('/tmp/pswd.txt')
pswdFile.createNewFile()
pswdFile.withWriter { currentObject ->
    currentObject.writeLine "${user}"
    currentObject.writeLine "${passwd}"
}
println("Password for ${user}: \"${passwd}\"")

// Запись данных в JSON файл
def jsonFile = new File('/tmp/user_data.json')
jsonFile.text = JsonOutput.toJson(userData)
println("User data saved to ${jsonFile.absolutePath}")

@Field final def REGULAR_PASSWORD_CHARACTERS = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()~?.,'

// Функция генерации пароля
def generatePassword(length) {
    if (length < 0) throw new IllegalArgumentException("length($length) is < 0")

    def secureRandom = new SecureRandom()
    (0..<length)
            .collect { REGULAR_PASSWORD_CHARACTERS[secureRandom.nextInt(REGULAR_PASSWORD_CHARACTERS.length())] }
            .join ''
}
