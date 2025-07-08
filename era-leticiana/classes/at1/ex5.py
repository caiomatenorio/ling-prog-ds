def generate_multiplication_table(x):
    return [x * i for i in range(1, 10)]

def ask_for_number():
    while True:
        try:
            x = int(input("Insira um número inteiro: "))
            return x
        except ValueError:
            print("Entrada inválida. Tente novamente.")

def main():
    x = ask_for_number()
    table = generate_multiplication_table(x)
    print(f"Tabela de multiplicação de {x}:")
    for i, value in enumerate(table, start=1):
        print(f"{x} x {i} = {value}")

if __name__ == "__main__":
    main()