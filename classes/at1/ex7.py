import random

def roll_dice():
    return random.randint(1, 6)

def main():
    print("Bem-vindo ao jogo de dados!")
    print(f"Você rodou um dado e obteve: {roll_dice()}")

if __name__ == "__main__":
    main()