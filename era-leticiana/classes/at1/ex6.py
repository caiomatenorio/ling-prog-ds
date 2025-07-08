def count_vowels(phrase):
    count = 0
    for char in phrase:
        if char.lower() in "aeiou":
            count += 1
    return count

def ask_for_phrase():
    phrase = input("Insira uma frase: ")
    return phrase
    
def main():
    phrase = ask_for_phrase()
    count = count_vowels(phrase)
    print(f"A frase contém {count} vogais.")

if __name__ == "__main__":
    main()