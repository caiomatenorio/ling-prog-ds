def km_to_miles(km):
    return km * 0.621371

def m_to_cm(m):
    return m * 100

def l_to_ml(l):
    return l * 1000

def ask_operation():
    while True:
        try:
            op = int(input("Escolha a operação (1: km to miles, 2: m to cm, 3: l to ml): "))
            if op in (1, 2, 3):
                return op
            else:
                print("Operação inválida.")
        except ValueError:
            print("Entrada inválida.")

def ask_value():
    while True:
        try:
            value = float(input("Insira o valor: "))
            return value
        except ValueError:
            print("Entrada inválida.")

def main():
    op = ask_operation()
    value = ask_value()

    if op == 1:
        print(f"{value} km = {km_to_miles(value)} miles")
    elif op == 2:
        print(f"{value} m = {m_to_cm(value)} cm")
    elif op == 3:
        print(f"{value} l = {l_to_ml(value)} ml")