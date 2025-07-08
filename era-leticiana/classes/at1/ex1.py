def sum_2n(x, y):
    return x + y

def sub_2n(x, y):
    return x - y

def mult_2n(x, y):
    return x * y

def div_2n(x, y):
    return x / y

def ask_two_numbers():
    while True:
        try:
            x = int(input("Insira o primeiro número: "))
            y = int(input("Insira o segundo número: "))
            return x, y
        except ValueError:
            print("Entrada inválida.")

def main():
    x, y = ask_two_numbers()

    print(f"Soma: {sum_2n(x, y)}")
    print(f"Multiplicação: {mult_2n(x, y)}")

    try:
        print(f"Divisão: {div_2n(x, y)}")
    except ZeroDivisionError:
        print("Erro: Divisão por zero.")

if __name__ == "__main__":
    main()
