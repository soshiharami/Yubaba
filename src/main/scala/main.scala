package main

import scala.sys.exit
import scala.util.Random

class Human() {
    var name: String = _
    var age: Int = Random.nextInt(100)
    var isPig: Boolean = _
}

class Adults extends Human {
    var hasCreditCard: Boolean = _
    var isEatPermission: Boolean = _
}

class ifContract(User: Human) {
    val sc = new java.util.Scanner(System.in)
    var isContract: Boolean = false
    var signatureName: String = ""

    def signature(): Unit = {
        signatureName = sc.next()
        User.name = this.signatureName
        isContract = true
    }
}

class ifbaba(var User: Human) extends Adults {
    name = "yubaba"

    def initMessage(): Unit = {
        println("契約書だよ。そこに名前を書きな。")
        val contract = new ifContract(User)
        contract.signature()
        if (contract.signatureName == "") {
            println("なんだいここで働きたくないのかい")
            exit()
        }
        println("フン。" + contract.signatureName + "というのかい。贅沢な名だねぇ。")
    }

    def changeName(): Unit = {
        val newNameIndex: Int = Random.nextInt(User.name.length)
        User.name = User.name.substring(newNameIndex, newNameIndex + 1)
        println("今からお前の名前は" + User.name + "だ。いいかい、" + User.name + "だよ。分かったら返事をするんだ、" + User.name + "!!")
    }
}

class eitherContract() {
    val sc = new java.util.Scanner(System.in)
    var isContract: Boolean = false

    def signature(): Either[String, String] = {
        val tmpName = sc.nextLine()
        if (tmpName != "") Right(tmpName) else Left("名前がないよ")
    }
    var signatureName:Either[String, String] = signature()
}

class eitherbaba(var User: Human) extends Adults {
    name = "yubaba"

    def initMessage(): Unit = {
        println("契約書だよ。そこに名前を書きな。")
        val contract = new eitherContract()
        contract.signatureName match {
            case Right(v) =>
                println("フン。" + v + "というのかい。贅沢な名だねぇ。")
                User.name = v
            case Left(_) =>
                println("なんだいここで働きたくないのかい")
                exit()
        }
    }

    def changeName(): Unit = {
        val newNameIndex: Int = Random.nextInt(User.name.length)
        User.name = User.name.substring(newNameIndex, newNameIndex + 1)
        println("今からお前の名前は" + User.name + "だ。いいかい、" + User.name + "だよ。分かったら返事をするんだ、" + User.name + "!!")
    }
}

class Father extends Adults {
    name = "papa"
    hasCreditCard = true
    isEatPermission = false

    def eatFoods(): Unit = {
        if (hasCreditCard && !isEatPermission) isPig = true else isPig = false
    }
}

class Mother(var fiancee: Father) extends Adults {
    name = "mama"

    def eatFoods(): Unit = {
        if (fiancee.hasCreditCard && !fiancee.isEatPermission) isPig = true else isPig = false
    }
}

object Main {
    def main(args: Array[String]): Unit = {
        val sen = new Human
        val father = new Father()
        val mother = new Mother(father)
        val yubaba = new ifbaba(sen)

        father.eatFoods()
        mother.eatFoods()

        yubaba.initMessage()
        yubaba.changeName()
    }
}
