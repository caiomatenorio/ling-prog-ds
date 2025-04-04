def is_palyndrome(word):
    return word == word[::-1]

def ask_for_word():
    word = input("Insira uma palavra: ")
    return word

def main():
    word = ask_for_word()
    if is_palyndrome(word):
        print(f"A palavra '{word}' é um palíndromo.")
    else:
        print(f"A palavra '{word}' não é um palíndromo.")

if __name__ == "__main__":
    main()