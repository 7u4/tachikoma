package com.sourceforgery.tachikoma.maildelivery

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

fun String.homogenize(): String =
    replace("\r\n", "\n")
        .replace("\r", "\n")
        .trimStart('\n', ' ')
        .trimEnd('\n', ' ')

data class SampleMessage(
    val envelope: ByteArray,
    val htmlText: String,
    val plainText: String
)

val m1001 = SampleMessage(
    envelope = """
        |From: "Doug Sauder" <doug@example.com>
        |To: "Jürgen Schmürgen" <schmuergen@example.com>
        |Subject: Die Hasen und die Frösche (Microsoft Outlook 00)
        |Date: Wed, 17 May 2000 19:08:29 -0400
        |Message-ID: <NDBBIAKOPKHFGPLCODIGIEKBCHAA.doug@example.com>
        |MIME-Version: 1.0
        |Content-Type: text/plain;
        |	charset="iso-8859-1"
        |Content-Transfer-Encoding: 8bit
        |X-Priority: 3 (Normal)
        |X-MSMail-Priority: Normal
        |X-Mailer: Microsoft Outlook IMO, Build 9.0.2416 (9.0.2910.0)
        |Importance: Normal
        |X-MimeOLE: Produced By Microsoft MimeOLE V5.00.2314.1300
        |
        |Die Hasen und die Frösche
        |
        |Die Hasen klagten einst über ihre mißliche Lage; "wir leben", sprach ein
        |Redner, "in steter Furcht vor Menschen und Tieren, eine Beute der Hunde, der
        |Adler, ja fast aller Raubtiere! Unsere stete Angst ist ärger als der Tod
        |selbst. Auf, laßt uns ein für allemal sterben."
        |
        |In einem nahen Teich wollten sie sich nun ersäufen; sie eilten ihm zu;
        |allein das außerordentliche Getöse und ihre wunderbare Gestalt erschreckte
        |eine Menge Frösche, die am Ufer saßen, so sehr, daß sie aufs schnellste
        |untertauchten.
        |
        |"Halt", rief nun eben dieser Sprecher, "wir wollen das Ersäufen noch ein
        |wenig aufschieben, denn auch uns fürchten, wie ihr seht, einige Tiere,
        |welche also wohl noch unglücklicher sein müssen als wir."
    """.trimMargin().toByteArray(Charset.forName("ISO-8859-15")),
    plainText = """
        |Die Hasen und die Frösche
        |
        |Die Hasen klagten einst über ihre mißliche Lage; "wir leben", sprach ein
        |Redner, "in steter Furcht vor Menschen und Tieren, eine Beute der Hunde, der
        |Adler, ja fast aller Raubtiere! Unsere stete Angst ist ärger als der Tod
        |selbst. Auf, laßt uns ein für allemal sterben."
        |
        |In einem nahen Teich wollten sie sich nun ersäufen; sie eilten ihm zu;
        |allein das außerordentliche Getöse und ihre wunderbare Gestalt erschreckte
        |eine Menge Frösche, die am Ufer saßen, so sehr, daß sie aufs schnellste
        |untertauchten.
        |
        |"Halt", rief nun eben dieser Sprecher, "wir wollen das Ersäufen noch ein
        |wenig aufschieben, denn auch uns fürchten, wie ihr seht, einige Tiere,
        |welche also wohl noch unglücklicher sein müssen als wir."
        """.trimMargin(),
    htmlText = ""
)

val m1005 = SampleMessage(
    envelope = """
        |Message-ID: <39235FC5.276CCE00@example.com>
        |Date: Wed, 17 May 2000 23:13:09 -0400
        |From: Doug Sauder <dwsauder@example.com>
        |X-Mailer: Mozilla 4.7 [en] (WinNT; I)
        |X-Accept-Language: en
        |MIME-Version: 1.0
        |To: Heinz =?iso-8859-1?Q?M=FCller?= <mueller@example.com>
        |Subject: Die Hasen und die =?iso-8859-1?Q?Fr=F6sche?= (Netscape Messenger 4.7)
        |Content-Type: multipart/mixed;
        | boundary="------------A1E83A41894D3755390B838A"
        |
        |This is a multi-part message in MIME format.
        |--------------A1E83A41894D3755390B838A
        |Content-Type: multipart/alternative;
        | boundary="------------F03F94BA73D3B9E8C1B94D92"
        |
        |
        |--------------F03F94BA73D3B9E8C1B94D92
        |Content-Type: text/plain; charset=iso-8859-1
        |Content-Transfer-Encoding: quoted-printable
        |
        |[blue ball]
        |
        |Die Hasen und die Fr=F6sche
        |
        |Die Hasen klagten einst =FCber ihre mi=DFliche Lage; "wir leben", sprach =
        |ein
        |Redner, "in steter Furcht vor Menschen und Tieren, eine Beute der Hunde,
        |der Adler, ja fast aller Raubtiere! Unsere stete Angst ist =E4rger als de=
        |r
        |Tod selbst. Auf, la=DFt uns ein f=FCr allemal sterben."
        |
        |In einem nahen Teich wollten sie sich nun ers=E4ufen; sie eilten ihm zu;
        |allein das au=DFerordentliche Get=F6se und ihre wunderbare Gestalt
        |erschreckte eine Menge Fr=F6sche, die am Ufer sa=DFen, so sehr, da=DF sie=
        | aufs
        |schnellste untertauchten.
        |
        |"Halt", rief nun eben dieser Sprecher, "wir wollen das Ers=E4ufen noch ei=
        |n
        |wenig aufschieben, denn auch uns f=FCrchten, wie ihr seht, einige Tiere,
        |welche also wohl noch ungl=FCcklicher sein m=FCssen als wir."
        |
        |[Image]
        |
        |
        |
        |--------------F03F94BA73D3B9E8C1B94D92
        |Content-Type: multipart/related;
        | boundary="------------C02FA3D0A04E95F295FB25EB"
        |
        |
        |--------------C02FA3D0A04E95F295FB25EB
        |Content-Type: text/html; charset=us-ascii
        |Content-Transfer-Encoding: 7bit
        |
        |<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
        |<html>
        |<img SRC="cid:part1.39235FC5.E71D8178@example.com" ALT="blue ball" height=27 width=27><b></b>
        |<p><b>Die Hasen und die Fr&ouml;sche</b>
        |<p>Die Hasen klagten einst &uuml;ber ihre mi&szlig;liche Lage; "wir leben",
        |sprach ein Redner, "in steter Furcht vor Menschen und Tieren, eine Beute
        |der Hunde, der Adler, ja fast aller Raubtiere! Unsere stete Angst ist &auml;rger
        |als der Tod selbst. Auf, la&szlig;t uns ein f&uuml;r allemal sterben."
        |<p>In einem nahen Teich wollten sie sich nun ers&auml;ufen; sie eilten
        |ihm zu; allein das au&szlig;erordentliche Get&ouml;se und ihre wunderbare
        |Gestalt erschreckte eine Menge Fr&ouml;sche, die am Ufer sa&szlig;en, so
        |sehr, da&szlig; sie aufs schnellste untertauchten.
        |<p>"Halt", rief nun eben dieser Sprecher, "wir wollen das Ers&auml;ufen
        |noch ein wenig aufschieben, denn auch uns f&uuml;rchten, wie ihr seht,
        |einige Tiere, welche also wohl noch ungl&uuml;cklicher sein m&uuml;ssen
        |als wir."
        |<p><img SRC="cid:part2.39235FC5.E71D8178@example.com" height=27 width=27>
        |<br>&nbsp;
        |<br>&nbsp;</html>
        |
        |--------------C02FA3D0A04E95F295FB25EB
        |Content-Type: image/png
        |Content-ID: <part1.39235FC5.E71D8178@example.com>
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline; filename="C:\TEMP\nsmailEG.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAgAABAAABgA
        |AAAACCkAEEIAEEoACDEAEFIIIXMIKXsIKYQIIWsAGFoACDkIIWMQOZwYQqUYQq0YQrUQOaUQ
        |MZQAGFIQMYwpUrU5Y8Y5Y84pWs4YSs4YQs4YQr1Ca8Z7nNacvd6Mtd5jlOcxa94hUt4YStYY
        |QsYQMaUAACHO5+/n7++cxu9ShO8pWucQOa1Ke86tzt6lzu9ajO8QMZxahNat1ufO7++Mve9K
        |e+8YOaUYSsaMvee15++Uve8AAClajOdzpe9rnO8IKYwxY+8pWu8IIXsAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADB
        |Mg1VAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAGI
        |SURBVHicddJtV5swGAbgEk6AJhBSk4bMCUynBSLaqovbrG/bfPn/vyh70lbsscebL5xznTsh
        |5BmNhgQoRChwo50EOIohUYLDj4zHhKYQkrEoQdvock4ne0IKMVUpKZLQDeqSTIsv+18PyqqW
        |Uw2IBsRM7307PPp+fDJrWtnpLDJvewYxnewfnvanZ+fzpmwXijC8KbqEa3Fx2ff91Y95U9XC
        |UpaDeQwiMpHXP/v+1++bWVPWQoGFawtjury9vru/f/C1Vi7ezT0WWpQHf/7+u/G71aLThK/M
        |jRxmT6KdzZ9fGk9yatMsTgZLl3XVgFRAC6spj/13enssqJVtWVa3NdBSacL8+VZmYqKmdd1C
        |SYoOiMOSGwtzlqqlFFIuOqv0a1ZEZrUkWICLLFW266y1KvWE1zV/iDAH1EopnVLCiygZCIom
        |H3NCKX0lnI+B1iuuzCGTxwXjnDO4d7NpbX42YJJHkBwmAm2TxwAZg40J3+Xtbv1rgOAZwG0N
        |xW62p+lT+Yi747sD/wEUVMzYmWkOvwAAACV0RVh0Q29tbWVudABjbGlwMmdpZiB2LjAuNiBi
        |eSBZdmVzIFBpZ3VldDZzO7wAAAAASUVORK5CYII=
        |--------------C02FA3D0A04E95F295FB25EB
        |Content-Type: image/png
        |Content-ID: <part2.39235FC5.E71D8178@example.com>
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline; filename="C:\TEMP\nsmail39.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAABAAALAAAV
        |AAAaAAAXAAARAAAKAAADAAAcAAAyAABEAABNAABIAAA9AAAjAAAWAAAmAABhAAB7AACGAACH
        |AAB9AAB0AABgAAA5AAAUAAAGAAAnAABLAABvAACQAAClAAC7AAC/AACrAAChAACMAABzAABb
        |AAAuAAAIAABMAAB3AACZAAC0GRnKODjVPT3bKSndBQW4AACoAAB5AAAxAAAYAAAEAABFAACa
        |AAC7JCTRYWHfhITmf3/mVlbqHx/SAAC5AACjAABdAABCAAAoAAAJAABnAAC6Dw/QVFTek5Pl
        |rKzpmZntZWXvJSXXAADBAACxAACcAABtAABTAAA2AAAbAAAFAABKAACBAADLICDdZ2fonJzr
        |pqbtiorvUVHvFBTRAADDAAC2AAB4AABeAABAAAAiAABXAACSAADCAADaGxvoVVXseHjveHjv
        |V1fvJibhAADOAAC3AACnAACVAABHAAArAAAPAACdAADFAADhBQXrKCjvPDzvNTXvGxvjAADQ
        |AADJAAC1AACXAACEAABsAABPAAASAAACAABiAADpAADvAgLnAADYAADLAAC6AACwAABwAAAT
        |AAAkAABYAADIAADTAADNAACzAACDAABuAAAeAAB+AADAAACkAACNAAB/AABpAABQAAAwAACR
        |AACpAAC8AACqAACbAABlAABJAAAqAAAOAAA0AACsAACvAACtAACmAACJAAB6AABrAABaAAA+
        |AAApAABqAACCAACfAACeAACWAACPAAB8AAAZAAAHAABVAACOAACKAAA4AAAQAAA/AAByAACA
        |AABcAAA3AAAsAABmAABDAABWAAAgAAAzAAA8AAA6AAAfAAAMAAAdAAANAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD8
        |LtlFAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAII
        |SURBVHicY2CAg/8QwIABmJhZWFnZ2Dk4MaU5uLh5eHn5+LkFBDlQJf8zC/EIi4iKiUtI8koJ
        |Scsgyf5nlpWTV1BUUlZRVVPX4NFk1UJIyghp6+jq6RsYGhmbKJgK85mZW8Dk/rNaSlhZ29ja
        |2Ts4Ojkr6Li4urFDNf53N/Ow8vTy9vH18w8IDAoWDQkNC4+ASP5ni4wKio6JjYtPSExKTnFW
        |SE1LF4A69n9GZlZ2Tm5efkFhUXFySWlZlEd5RSVY7j+TkGRVdU1tXX1DY1Ozcktpa1t7h2Yn
        |OAj+d7l1tyo79vT29SdNSJ44SbFVdHIo9xSIHNPUaWqTpifNSJrZnK00S0U1a/acUG5piNz/
        |uXLzVJ2qm6dXz584S2WB1cJFi5cshZr539xVftnyFKUVTi2TVjqvyhJLXb1m7TqoHPt6F/HW
        |0g0bN63crGqVtWXrtu07BJihcsw71+zanRW8Z89eq337RQ/Ip60xO3gIElX/LbikDm8T36Kw
        |bNmRo7O3zpHkPSZwHBqL//8flz1x2OOkyKJTi7aqbzutfUZI2gIuF8F2lr/D5dw2+fZdwpl8
        |YVOlI+CJ4/9/joOyYed5QzMvhGqnm2V0WiClm///D0lfXHtJ6vLlK9w7rx7vQk5SQJbFtSms
        |1y9evXid7QZacgOxmSxktNzdtSwwU+J/VICaCPFIYU3XAJhIOtjf5sfyAAAAJXRFWHRDb21t
        |ZW50AGNsaXAyZ2lmIHYuMC42IGJ5IFl2ZXMgUGlndWV0NnM7vAAAAABJRU5ErkJggg==
        |--------------C02FA3D0A04E95F295FB25EB--
        |
        |--------------F03F94BA73D3B9E8C1B94D92--
        |
        |--------------A1E83A41894D3755390B838A
        |Content-Type: image/png;
        | name="redball.png"
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline;
        | filename="redball.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAABAAALAAAV
        |AAAaAAAXAAARAAAKAAADAAAcAAAyAABEAABNAABIAAA9AAAjAAAWAAAmAABhAAB7AACGAACH
        |AAB9AAB0AABgAAA5AAAUAAAGAAAnAABLAABvAACQAAClAAC7AAC/AACrAAChAACMAABzAABb
        |AAAuAAAIAABMAAB3AACZAAC0GRnKODjVPT3bKSndBQW4AACoAAB5AAAxAAAYAAAEAABFAACa
        |AAC7JCTRYWHfhITmf3/mVlbqHx/SAAC5AACjAABdAABCAAAoAAAJAABnAAC6Dw/QVFTek5Pl
        |rKzpmZntZWXvJSXXAADBAACxAACcAABtAABTAAA2AAAbAAAFAABKAACBAADLICDdZ2fonJzr
        |pqbtiorvUVHvFBTRAADDAAC2AAB4AABeAABAAAAiAABXAACSAADCAADaGxvoVVXseHjveHjv
        |V1fvJibhAADOAAC3AACnAACVAABHAAArAAAPAACdAADFAADhBQXrKCjvPDzvNTXvGxvjAADQ
        |AADJAAC1AACXAACEAABsAABPAAASAAACAABiAADpAADvAgLnAADYAADLAAC6AACwAABwAAAT
        |AAAkAABYAADIAADTAADNAACzAACDAABuAAAeAAB+AADAAACkAACNAAB/AABpAABQAAAwAACR
        |AACpAAC8AACqAACbAABlAABJAAAqAAAOAAA0AACsAACvAACtAACmAACJAAB6AABrAABaAAA+
        |AAApAABqAACCAACfAACeAACWAACPAAB8AAAZAAAHAABVAACOAACKAAA4AAAQAAA/AAByAACA
        |AABcAAA3AAAsAABmAABDAABWAAAgAAAzAAA8AAA6AAAfAAAMAAAdAAANAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD8
        |LtlFAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAII
        |SURBVHicY2CAg/8QwIABmJhZWFnZ2Dk4MaU5uLh5eHn5+LkFBDlQJf8zC/EIi4iKiUtI8koJ
        |Scsgyf5nlpWTV1BUUlZRVVPX4NFk1UJIyghp6+jq6RsYGhmbKJgK85mZW8Dk/rNaSlhZ29ja
        |2Ts4Ojkr6Li4urFDNf53N/Ow8vTy9vH18w8IDAoWDQkNC4+ASP5ni4wKio6JjYtPSExKTnFW
        |SE1LF4A69n9GZlZ2Tm5efkFhUXFySWlZlEd5RSVY7j+TkGRVdU1tXX1DY1Ozcktpa1t7h2Yn
        |OAj+d7l1tyo79vT29SdNSJ44SbFVdHIo9xSIHNPUaWqTpifNSJrZnK00S0U1a/acUG5piNz/
        |uXLzVJ2qm6dXz584S2WB1cJFi5cshZr539xVftnyFKUVTi2TVjqvyhJLXb1m7TqoHPt6F/HW
        |0g0bN63crGqVtWXrtu07BJihcsw71+zanRW8Z89eq337RQ/Ip60xO3gIElX/LbikDm8T36Kw
        |bNmRo7O3zpHkPSZwHBqL//8flz1x2OOkyKJTi7aqbzutfUZI2gIuF8F2lr/D5dw2+fZdwpl8
        |YVOlI+CJ4/9/joOyYed5QzMvhGqnm2V0WiClm///D0lfXHtJ6vLlK9w7rx7vQk5SQJbFtSms
        |1y9evXid7QZacgOxmSxktNzdtSwwU+J/VICaCPFIYU3XAJhIOtjf5sfyAAAAJXRFWHRDb21t
        |ZW50AGNsaXAyZ2lmIHYuMC42IGJ5IFl2ZXMgUGlndWV0NnM7vAAAAABJRU5ErkJggg==
        |--------------A1E83A41894D3755390B838A
        |Content-Type: image/png;
        | name="greenball.png"
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline;
        | filename="greenball.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAAAEAAAGAAA
        |IQAACAAAMQAAQgAAUgAAWgAASgAIYwAIcwAIewAQjAAIawAAOQAAYwAQlAAQnAAhpQAQpQAh
        |rQBCvRhjxjFjxjlSxiEpzgAYvQAQrQAYrQAhvQCU1mOt1nuE1lJK3hgh1gAYxgAYtQAAKQBC
        |zhDO55Te563G55SU52NS5yEh3gAYzgBS3iGc52vW75y974yE71JC7xCt73ul3nNa7ykh5wAY
        |1gAx5wBS7yFr7zlK7xgp5wAp7wAx7wAIhAAQtQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAp
        |1fnZAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAFt
        |SURBVHicddJtV8IgFAdwD2zIgMEE1+NcqdsoK+m5tCyz7/+ZiLmHsyzvq53zO/cy+N9ery1b
        |Ve9PWQA9z4MQ+H8Yoj7GASZ95IHfaBGmLOSchyIgyOu22mgQSjUcDuNYcoGjLiLK1cHh0fHJ
        |aTKKOcMItgYxT89OzsfjyTTLC8UF0c2ZNmKquJhczq6ub+YmSVUYRF59GeDastu7+9nD41Nm
        |kiJ2jc2J3kAWZ9Pr55fH18XSmRuKUTXUaqHy7O19tfr4NFle/w3YDrWRUIlZrL/W86XJkyJV
        |G9EaEjIx2XyZmZJGioeUaL+2AY8TY8omR6nkLKhu70zjUKVJXsp3quS2DVSJWNh3zzJKCyex
        |I0ZxBP3afE0ElyqOlZJyw8r3BE2SFiJCyxA434SCkg65RhdeQBljQtCg39LWrA90RDDG1EWr
        |YUO23hMANUKRRl61E529cR++D2G5LK002dr/qrcfu9u0V3bxn/XdhR/NYeeN0ggsLAAAACV0
        |RVh0Q29tbWVudABjbGlwMmdpZiB2LjAuNiBieSBZdmVzIFBpZ3VldDZzO7wAAAAASUVORK5C
        |YII=
        |--------------A1E83A41894D3755390B838A--
        |
        """.trimMargin().toByteArray(Charsets.ISO_8859_1),
    plainText = """
            |[blue ball]
            |
            |Die Hasen und die Frösche
            |
            |Die Hasen klagten einst über ihre mißliche Lage; "wir leben", sprach ein
            |Redner, "in steter Furcht vor Menschen und Tieren, eine Beute der Hunde,
            |der Adler, ja fast aller Raubtiere! Unsere stete Angst ist ärger als der
            |Tod selbst. Auf, laßt uns ein für allemal sterben."
            |
            |In einem nahen Teich wollten sie sich nun ersäufen; sie eilten ihm zu;
            |allein das außerordentliche Getöse und ihre wunderbare Gestalt
            |erschreckte eine Menge Frösche, die am Ufer saßen, so sehr, daß sie aufs
            |schnellste untertauchten.
            |
            |"Halt", rief nun eben dieser Sprecher, "wir wollen das Ersäufen noch ein
            |wenig aufschieben, denn auch uns fürchten, wie ihr seht, einige Tiere,
            |welche also wohl noch unglücklicher sein müssen als wir."
            |
            |[Image]
            |
            |
            """.trimMargin().homogenize(),

    htmlText = """
            |<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
            |<html>
            |<img SRC="cid:part1.39235FC5.E71D8178@example.com" ALT="blue ball" height=27 width=27><b></b>
            |<p><b>Die Hasen und die Fr&ouml;sche</b>
            |<p>Die Hasen klagten einst &uuml;ber ihre mi&szlig;liche Lage; "wir leben",
            |sprach ein Redner, "in steter Furcht vor Menschen und Tieren, eine Beute
            |der Hunde, der Adler, ja fast aller Raubtiere! Unsere stete Angst ist &auml;rger
            |als der Tod selbst. Auf, la&szlig;t uns ein f&uuml;r allemal sterben."
            |<p>In einem nahen Teich wollten sie sich nun ers&auml;ufen; sie eilten
            |ihm zu; allein das au&szlig;erordentliche Get&ouml;se und ihre wunderbare
            |Gestalt erschreckte eine Menge Fr&ouml;sche, die am Ufer sa&szlig;en, so
            |sehr, da&szlig; sie aufs schnellste untertauchten.
            |<p>"Halt", rief nun eben dieser Sprecher, "wir wollen das Ers&auml;ufen
            |noch ein wenig aufschieben, denn auch uns f&uuml;rchten, wie ihr seht,
            |einige Tiere, welche also wohl noch ungl&uuml;cklicher sein m&uuml;ssen
            |als wir."
            |<p><img SRC="cid:part2.39235FC5.E71D8178@example.com" height=27 width=27>
            |<br>&nbsp;
            |<br>&nbsp;</html>
        """.trimMargin().homogenize()
)

val m1006 = SampleMessage(
    envelope = """
        |Message-ID: <39236103.FFE674FC@example.com>
        |Date: Wed, 17 May 2000 23:18:27 -0400
        |From: Doug Sauder <dwsauder@example.com>
        |X-Mailer: Mozilla 4.7 [en] (WinNT; I)
        |X-Accept-Language: en
        |MIME-Version: 1.0
        |To: =?iso-8859-1?Q?J=FCrgen=20Schm=FCrgen?= <schmuergen@example.com>
        |Subject: Test message from Netscape Communicator 4.7
        |Content-Type: multipart/mixed;
        | boundary="------------B7133A01A6B323BF00DBC9A7"
        |
        |This is a multi-part message in MIME format.
        |--------------B7133A01A6B323BF00DBC9A7
        |Content-Type: multipart/related;
        | boundary="------------8E6A06810565BCAB5E1F7D97"
        |
        |
        |--------------8E6A06810565BCAB5E1F7D97
        |Content-Type: text/html; charset=us-ascii
        |Content-Transfer-Encoding: 7bit
        |
        |<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
        |<html>
        |<img SRC="cid:part1.39236103.1B697A54@example.com" ALT="blue ball" height=27 width=27><b></b>
        |<p><b><font size=+2>Die Hasen und die Fr&ouml;sche</font></b>
        |<p>Die Hasen klagten einst &uuml;ber ihre mi&szlig;liche Lage; "wir leben",
        |sprach ein Redner, "in steter Furcht vor Menschen und Tieren, eine Beute
        |der Hunde, der Adler, ja fast aller Raubtiere! Unsere stete Angst ist &auml;rger
        |als der Tod selbst. Auf, la&szlig;t uns ein f&uuml;r allemal sterben."
        |<p>In einem nahen Teich wollten sie sich nun ers&auml;ufen; sie eilten
        |ihm zu; allein das au&szlig;erordentliche Get&ouml;se und ihre wunderbare
        |Gestalt erschreckte eine Menge Fr&ouml;sche, die am Ufer sa&szlig;en, so
        |sehr, da&szlig; sie aufs schnellste untertauchten.
        |<p>"Halt", rief nun eben dieser Sprecher, "wir wollen das Ers&auml;ufen
        |noch ein wenig aufschieben, denn auch uns f&uuml;rchten, wie ihr seht,
        |einige Tiere, welche also wohl noch ungl&uuml;cklicher sein m&uuml;ssen
        |als wir."
        |<p><img SRC="cid:part2.39236103.1B697A54@example.com" ALT="red ball" height=27 width=27></html>
        |
        |--------------8E6A06810565BCAB5E1F7D97
        |Content-Type: image/png
        |Content-ID: <part1.39236103.1B697A54@example.com>
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline; filename="C:\TEMP\nsmailV0.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAgAABAAABgA
        |AAAACCkAEEIAEEoACDEAEFIIIXMIKXsIKYQIIWsAGFoACDkIIWMQOZwYQqUYQq0YQrUQOaUQ
        |MZQAGFIQMYwpUrU5Y8Y5Y84pWs4YSs4YQs4YQr1Ca8Z7nNacvd6Mtd5jlOcxa94hUt4YStYY
        |QsYQMaUAACHO5+/n7++cxu9ShO8pWucQOa1Ke86tzt6lzu9ajO8QMZxahNat1ufO7++Mve9K
        |e+8YOaUYSsaMvee15++Uve8AAClajOdzpe9rnO8IKYwxY+8pWu8IIXsAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADB
        |Mg1VAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAGI
        |SURBVHicddJtV5swGAbgEk6AJhBSk4bMCUynBSLaqovbrG/bfPn/vyh70lbsscebL5xznTsh
        |5BmNhgQoRChwo50EOIohUYLDj4zHhKYQkrEoQdvock4ne0IKMVUpKZLQDeqSTIsv+18PyqqW
        |Uw2IBsRM7307PPp+fDJrWtnpLDJvewYxnewfnvanZ+fzpmwXijC8KbqEa3Fx2ff91Y95U9XC
        |UpaDeQwiMpHXP/v+1++bWVPWQoGFawtjury9vru/f/C1Vi7ezT0WWpQHf/7+u/G71aLThK/M
        |jRxmT6KdzZ9fGk9yatMsTgZLl3XVgFRAC6spj/13enssqJVtWVa3NdBSacL8+VZmYqKmdd1C
        |SYoOiMOSGwtzlqqlFFIuOqv0a1ZEZrUkWICLLFW266y1KvWE1zV/iDAH1EopnVLCiygZCIom
        |H3NCKX0lnI+B1iuuzCGTxwXjnDO4d7NpbX42YJJHkBwmAm2TxwAZg40J3+Xtbv1rgOAZwG0N
        |xW62p+lT+Yi747sD/wEUVMzYmWkOvwAAACV0RVh0Q29tbWVudABjbGlwMmdpZiB2LjAuNiBi
        |eSBZdmVzIFBpZ3VldDZzO7wAAAAASUVORK5CYII=
        |--------------8E6A06810565BCAB5E1F7D97
        |Content-Type: image/png
        |Content-ID: <part2.39236103.1B697A54@example.com>
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline; filename="C:\TEMP\nsmailNM.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAABAAALAAAV
        |AAAaAAAXAAARAAAKAAADAAAcAAAyAABEAABNAABIAAA9AAAjAAAWAAAmAABhAAB7AACGAACH
        |AAB9AAB0AABgAAA5AAAUAAAGAAAnAABLAABvAACQAAClAAC7AAC/AACrAAChAACMAABzAABb
        |AAAuAAAIAABMAAB3AACZAAC0GRnKODjVPT3bKSndBQW4AACoAAB5AAAxAAAYAAAEAABFAACa
        |AAC7JCTRYWHfhITmf3/mVlbqHx/SAAC5AACjAABdAABCAAAoAAAJAABnAAC6Dw/QVFTek5Pl
        |rKzpmZntZWXvJSXXAADBAACxAACcAABtAABTAAA2AAAbAAAFAABKAACBAADLICDdZ2fonJzr
        |pqbtiorvUVHvFBTRAADDAAC2AAB4AABeAABAAAAiAABXAACSAADCAADaGxvoVVXseHjveHjv
        |V1fvJibhAADOAAC3AACnAACVAABHAAArAAAPAACdAADFAADhBQXrKCjvPDzvNTXvGxvjAADQ
        |AADJAAC1AACXAACEAABsAABPAAASAAACAABiAADpAADvAgLnAADYAADLAAC6AACwAABwAAAT
        |AAAkAABYAADIAADTAADNAACzAACDAABuAAAeAAB+AADAAACkAACNAAB/AABpAABQAAAwAACR
        |AACpAAC8AACqAACbAABlAABJAAAqAAAOAAA0AACsAACvAACtAACmAACJAAB6AABrAABaAAA+
        |AAApAABqAACCAACfAACeAACWAACPAAB8AAAZAAAHAABVAACOAACKAAA4AAAQAAA/AAByAACA
        |AABcAAA3AAAsAABmAABDAABWAAAgAAAzAAA8AAA6AAAfAAAMAAAdAAANAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD8
        |LtlFAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAII
        |SURBVHicY2CAg/8QwIABmJhZWFnZ2Dk4MaU5uLh5eHn5+LkFBDlQJf8zC/EIi4iKiUtI8koJ
        |Scsgyf5nlpWTV1BUUlZRVVPX4NFk1UJIyghp6+jq6RsYGhmbKJgK85mZW8Dk/rNaSlhZ29ja
        |2Ts4Ojkr6Li4urFDNf53N/Ow8vTy9vH18w8IDAoWDQkNC4+ASP5ni4wKio6JjYtPSExKTnFW
        |SE1LF4A69n9GZlZ2Tm5efkFhUXFySWlZlEd5RSVY7j+TkGRVdU1tXX1DY1Ozcktpa1t7h2Yn
        |OAj+d7l1tyo79vT29SdNSJ44SbFVdHIo9xSIHNPUaWqTpifNSJrZnK00S0U1a/acUG5piNz/
        |uXLzVJ2qm6dXz584S2WB1cJFi5cshZr539xVftnyFKUVTi2TVjqvyhJLXb1m7TqoHPt6F/HW
        |0g0bN63crGqVtWXrtu07BJihcsw71+zanRW8Z89eq337RQ/Ip60xO3gIElX/LbikDm8T36Kw
        |bNmRo7O3zpHkPSZwHBqL//8flz1x2OOkyKJTi7aqbzutfUZI2gIuF8F2lr/D5dw2+fZdwpl8
        |YVOlI+CJ4/9/joOyYed5QzMvhGqnm2V0WiClm///D0lfXHtJ6vLlK9w7rx7vQk5SQJbFtSms
        |1y9evXid7QZacgOxmSxktNzdtSwwU+J/VICaCPFIYU3XAJhIOtjf5sfyAAAAJXRFWHRDb21t
        |ZW50AGNsaXAyZ2lmIHYuMC42IGJ5IFl2ZXMgUGlndWV0NnM7vAAAAABJRU5ErkJggg==
        |--------------8E6A06810565BCAB5E1F7D97--
        |
        |--------------B7133A01A6B323BF00DBC9A7
        |Content-Type: image/png;
        | name="greenball.png"
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline;
        | filename="greenball.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAAAEAAAGAAA
        |IQAACAAAMQAAQgAAUgAAWgAASgAIYwAIcwAIewAQjAAIawAAOQAAYwAQlAAQnAAhpQAQpQAh
        |rQBCvRhjxjFjxjlSxiEpzgAYvQAQrQAYrQAhvQCU1mOt1nuE1lJK3hgh1gAYxgAYtQAAKQBC
        |zhDO55Te563G55SU52NS5yEh3gAYzgBS3iGc52vW75y974yE71JC7xCt73ul3nNa7ykh5wAY
        |1gAx5wBS7yFr7zlK7xgp5wAp7wAx7wAIhAAQtQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAp
        |1fnZAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAFt
        |SURBVHicddJtV8IgFAdwD2zIgMEE1+NcqdsoK+m5tCyz7/+ZiLmHsyzvq53zO/cy+N9ery1b
        |Ve9PWQA9z4MQ+H8Yoj7GASZ95IHfaBGmLOSchyIgyOu22mgQSjUcDuNYcoGjLiLK1cHh0fHJ
        |aTKKOcMItgYxT89OzsfjyTTLC8UF0c2ZNmKquJhczq6ub+YmSVUYRF59GeDastu7+9nD41Nm
        |kiJ2jc2J3kAWZ9Pr55fH18XSmRuKUTXUaqHy7O19tfr4NFle/w3YDrWRUIlZrL/W86XJkyJV
        |G9EaEjIx2XyZmZJGioeUaL+2AY8TY8omR6nkLKhu70zjUKVJXsp3quS2DVSJWNh3zzJKCyex
        |I0ZxBP3afE0ElyqOlZJyw8r3BE2SFiJCyxA434SCkg65RhdeQBljQtCg39LWrA90RDDG1EWr
        |YUO23hMANUKRRl61E529cR++D2G5LK002dr/qrcfu9u0V3bxn/XdhR/NYeeN0ggsLAAAACV0
        |RVh0Q29tbWVudABjbGlwMmdpZiB2LjAuNiBieSBZdmVzIFBpZ3VldDZzO7wAAAAASUVORK5C
        |YII=
        |--------------B7133A01A6B323BF00DBC9A7
        |Content-Type: image/png;
        | name="blueball.png"
        |Content-Transfer-Encoding: base64
        |Content-Disposition: inline;
        | filename="blueball.png"
        |
        |iVBORw0KGgoAAAANSUhEUgAAABsAAAAbCAMAAAC6CgRnAAADAFBMVEX///8AAAgAABAAABgA
        |AAAACCkAEEIAEEoACDEAEFIIIXMIKXsIKYQIIWsAGFoACDkIIWMQOZwYQqUYQq0YQrUQOaUQ
        |MZQAGFIQMYwpUrU5Y8Y5Y84pWs4YSs4YQs4YQr1Ca8Z7nNacvd6Mtd5jlOcxa94hUt4YStYY
        |QsYQMaUAACHO5+/n7++cxu9ShO8pWucQOa1Ke86tzt6lzu9ajO8QMZxahNat1ufO7++Mve9K
        |e+8YOaUYSsaMvee15++Uve8AAClajOdzpe9rnO8IKYwxY+8pWu8IIXsAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        |AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADB
        |Mg1VAAAAAXRSTlMAQObYZgAAABZ0RVh0U29mdHdhcmUAZ2lmMnBuZyAyLjAuMT1evmgAAAGI
        |SURBVHicddJtV5swGAbgEk6AJhBSk4bMCUynBSLaqovbrG/bfPn/vyh70lbsscebL5xznTsh
        |5BmNhgQoRChwo50EOIohUYLDj4zHhKYQkrEoQdvock4ne0IKMVUpKZLQDeqSTIsv+18PyqqW
        |Uw2IBsRM7307PPp+fDJrWtnpLDJvewYxnewfnvanZ+fzpmwXijC8KbqEa3Fx2ff91Y95U9XC
        |UpaDeQwiMpHXP/v+1++bWVPWQoGFawtjury9vru/f/C1Vi7ezT0WWpQHf/7+u/G71aLThK/M
        |jRxmT6KdzZ9fGk9yatMsTgZLl3XVgFRAC6spj/13enssqJVtWVa3NdBSacL8+VZmYqKmdd1C
        |SYoOiMOSGwtzlqqlFFIuOqv0a1ZEZrUkWICLLFW266y1KvWE1zV/iDAH1EopnVLCiygZCIom
        |H3NCKX0lnI+B1iuuzCGTxwXjnDO4d7NpbX42YJJHkBwmAm2TxwAZg40J3+Xtbv1rgOAZwG0N
        |xW62p+lT+Yi747sD/wEUVMzYmWkOvwAAACV0RVh0Q29tbWVudABjbGlwMmdpZiB2LjAuNiBi
        |eSBZdmVzIFBpZ3VldDZzO7wAAAAASUVORK5CYII=
        |--------------B7133A01A6B323BF00DBC9A7--
    """.trimIndent().toByteArray(StandardCharsets.ISO_8859_1),
    plainText = "",
    htmlText = ""
)
