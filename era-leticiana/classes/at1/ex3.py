def ask_for_phrase():
    phrase = input("Insira uma frase: ")
    return phrase
    
def count_not_blank_chars(phrase):
    count = 0
    for char in phrase:
        if char != " ":
            count += 1
    return count

def main():
    phrase = ask_for_phrase()
    count = count_not_blank_chars(phrase)
    print(f"A frase contém {count} caracteres não em branco.")