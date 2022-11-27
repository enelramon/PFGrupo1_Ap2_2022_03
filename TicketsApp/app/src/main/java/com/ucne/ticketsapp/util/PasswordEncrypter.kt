package com.ucne.ticketsapp.util

fun stringEncrypter(input: String): String {
    var encryptedOutput: String = ""
    input.forEach {
        when (it) {
            'A' -> {
                encryptedOutput += 'e'
            }
            'a' -> {
                encryptedOutput += 't'
            }
            'B' -> {
                encryptedOutput += 'q'
            }
            'b' -> {
                encryptedOutput += 'k'
            }
            'C' -> {
                encryptedOutput += 'r'
            }
            'c' -> {
                encryptedOutput += 'M'
            }
            'D' -> {
                encryptedOutput += 's'
            }
            'd' -> {
                encryptedOutput += 'j'
            }
            'E' -> {
                encryptedOutput += 'f'
            }
            'e' -> {
                encryptedOutput += 'n'
            }
            'F' -> {
                encryptedOutput += 'g'
            }
            'f' -> {
                encryptedOutput += 'D'
            }
            'G' -> {
                encryptedOutput += 'p'
            }
            'g' -> {
                encryptedOutput += 'S'
            }
            'H' -> {
                encryptedOutput += 'G'
            }
            'h' -> {
                encryptedOutput += 'l'
            }
            'I' -> {
                encryptedOutput += 'F'
            }
            'i' -> {
                encryptedOutput += 'Q'
            }
            'J' -> {
                encryptedOutput += 'm'
            }
            'j' -> {
                encryptedOutput += 'B'
            }
            'K' -> {
                encryptedOutput += 'V'
            }
            'k' -> {
                encryptedOutput += 'h'
            }
            'L' -> {
                encryptedOutput += 'P'
            }
            'l' -> {
                encryptedOutput += 'A'
            }
            'M' -> {
                encryptedOutput += 'H'
            }
            'm' -> {
                encryptedOutput += 'Z'
            }
            'N' -> {
                encryptedOutput += 'o'
            }
            'n' -> {
                encryptedOutput += 'v'
            }
            'O' -> {
                encryptedOutput += 'K'
            }
            'o' -> {
                encryptedOutput += 'J'
            }
            'P' -> {
                encryptedOutput += 'b'
            }
            'p' -> {
                encryptedOutput += 'W'
            }
            'Q' -> {
                encryptedOutput += 'd'
            }
            'q' -> {
                encryptedOutput += 'E'
            }
            'R' -> {
                encryptedOutput += 'a'
            }
            'r' -> {
                encryptedOutput += 'i'
            }
            'S' -> {
                encryptedOutput += 'x'
            }
            's' -> {
                encryptedOutput += 'O'
            }
            'T' -> {
                encryptedOutput += 'L'
            }
            't' -> {
                encryptedOutput += 'w'
            }
            'U' -> {
                encryptedOutput += 'R'
            }
            'u' -> {
                encryptedOutput += 'T'
            }
            'V' -> {
                encryptedOutput += 'y'
            }
            'v' -> {
                encryptedOutput += 'X'
            }
            'W' -> {
                encryptedOutput += 'N'
            }
            'w' -> {
                encryptedOutput += 'U'
            }
            'X' -> {
                encryptedOutput += 'c'
            }
            'x' -> {
                encryptedOutput += 'I'
            }
            'Y' -> {
                encryptedOutput += 'z'
            }
            'y' -> {
                encryptedOutput += 'C'
            }
            'Z' -> {
                encryptedOutput += 'Y'
            }
            'z' -> {
                encryptedOutput += 'u'
            }

            else -> {
                encryptedOutput += it
            }
        }
    }
    return encryptedOutput
}