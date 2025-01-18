import java.security.SecureRandom
import groovy.transform.Field


String user = "otus_admin"
String passwd =  generatePassword(25)
//def pswd = new File('C:/Users/Поваринцевы/Desktop/Работа Саша/vagrant/pswd.txt')
def pswd = new File('/tmp/pswd.txt')
pswd.createNewFile()
pswd.withWriter {currentObject ->
    currentObject.writeLine "${user}"
    currentObject.writeLine "${passwd}"
}
println("Password for ${user}: \"${passwd}\"")

@Field final def REGULAR_PASSWORD_CHARACTERS = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()~?.,'
@Field final def PASSWORD_CHARACTERS = REGULAR_PASSWORD_CHARACTERS + '!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ '


def generatePassword(length) {
    if (length < 0) throw new IllegalArgumentException("length($length) is < 0")

    def secureRandom = new SecureRandom()
    (0..<length)
            .collect { REGULAR_PASSWORD_CHARACTERS[secureRandom.nextInt(REGULAR_PASSWORD_CHARACTERS.length())] }
            .join ''
}
