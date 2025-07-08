def ask_for_three_grades():
    while True:
        try:
            grades = []
            for i in range(3):
                grade = float(input(f"Insira a {i + 1}ª nota: "))
                grades.append(grade)
            return grades
        except ValueError:
            print("Entrada inválida.")

def calculate_average(grades):
    return sum(grades) / len(grades)

def main():
    grades = ask_for_three_grades()
    average = calculate_average(grades)
    print(f"A média das notas é: {average:.2f}")

if __name__ == "__main__":
    main()