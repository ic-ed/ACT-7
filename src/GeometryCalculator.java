import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeometryCalculator {
    private static final double PI = 3.1416;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> results = new ArrayList<>();

        while (true) {
            int shapeChoice = askShapeMenu(input);
            if (shapeChoice == 0) break;

            int operationChoice = askOperationMenu(input);
            if (operationChoice == 0) continue;

            try {
                double result = calculate(shapeChoice, operationChoice, input);
                results.add(result);
                System.out.println("Resultado: " + result);
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }

        System.out.println("Resultados almacenados:");
        for (double value : results) {
            System.out.println(value);
        }
    }

    private static int askShapeMenu(Scanner input) {
        System.out.println("\nElige una figura:");
        System.out.println("1. Círculo");
        System.out.println("2. Cuadrado");
        System.out.println("3. Triángulo");
        System.out.println("4. Rectángulo");
        System.out.println("5. Pentágono");
        System.out.println("0. Salir");
        return readMenuOption(input, 5);
    }

    private static int askOperationMenu(Scanner input) {
        System.out.println("Elige una operación:");
        System.out.println("1. Área");
        System.out.println("2. Perímetro");
        System.out.println("3. Potencia");
        System.out.println("0. Volver");
        return readMenuOption(input, 3);
    }

    private static int readMenuOption(Scanner input, int max) {
        int option;
        while (true) {
            System.out.print("Opción: ");
            try {
                option = Integer.parseInt(input.nextLine().trim());
                if (option >= 0 && option <= max)
                    return option;
                System.out.println("Opción fuera de rango.");
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número válido.");
            }
        }
    }

    private static double calculate(int shapeChoice, int operationChoice, Scanner input) {
        if (operationChoice == 3)
            return handlePower(input);
        return switch (shapeChoice) {
            case 1 -> operationChoice == 1 ? circleArea(input) : circlePerimeter(input);
            case 2 -> operationChoice == 1 ? squareArea(input) : squarePerimeter(input);
            case 3 -> operationChoice == 1 ? triangleArea(input) : trianglePerimeter(input);
            case 4 -> operationChoice == 1 ? rectangleArea(input) : rectanglePerimeter(input);
            case 5 -> operationChoice == 1 ? pentagonArea(input) : pentagonPerimeter(input);
            default -> throw new IllegalArgumentException("Figura desconocida.");
        };
    }

    private static double handlePower(Scanner input) {
        System.out.println("Base:");
        double base = readPositiveDouble(input);
        System.out.println("Exponente (entero, puede ser 0 o positivo):");
        int exponent = readPositiveInt(input);
        return powerRecursive(base, exponent);
    }

    private static int readPositiveInt(Scanner input) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(input.nextLine().trim());
                if (value >= 0) return value;
                System.out.println("Debe ser 0 o mayor.");
            } catch (NumberFormatException e) {
                System.out.println("Número inválido.");
            }
        }
    }

    private static double powerRecursive(double base, int exponent) {
        if (exponent == 0)
            return 1;
        return base * powerRecursive(base, exponent - 1);
    }

    private static double circleArea(Scanner input) {
        System.out.print("Radio: ");
        double radius = readPositiveDouble(input);
        return PI * radius * radius;
    }

    private static double circlePerimeter(Scanner input) {
        System.out.println("Radio:");
        return 2 * PI * readPositiveDouble(input);
    }

    private static double squareArea(Scanner input) {
        System.out.println("Lado:");
        double side = readPositiveDouble(input);
        return side * side;
    }

    private static double squarePerimeter(Scanner input) {
        System.out.println("Lado:");
        return 4 * readPositiveDouble(input);
    }

    private static double triangleArea(Scanner input) {
        System.out.println("Base:");
        double base = readPositiveDouble(input);
        System.out.println("Altura:");
        double height = readPositiveDouble(input);
        return 0.5 * base * height;
    }

    private static double trianglePerimeter(Scanner input) {
        System.out.println("Lado 1:");
        double a = readPositiveDouble(input);
        System.out.println("Lado 2:");
        double b = readPositiveDouble(input);
        System.out.println("Lado 3:");
        double c = readPositiveDouble(input);
        return a + b + c;
    }

    private static double rectangleArea(Scanner input) {
        System.out.println("Base:");
        double base = readPositiveDouble(input);
        System.out.println("Altura:");
        double height = readPositiveDouble(input);
        return base * height;
    }

    private static double rectanglePerimeter(Scanner input) {
        System.out.println("Base:");
        double base = readPositiveDouble(input);
        System.out.println("Altura:");
        double height = readPositiveDouble(input);
        return 2 * (base + height);
    }

    private static double pentagonArea(Scanner input) {
        System.out.println("Lado:");
        double side = readPositiveDouble(input);
        System.out.println("Apotema:");
        double apothem = readPositiveDouble(input);
        return (5 * side * apothem) / 2;
    }

    private static double pentagonPerimeter(Scanner input) {
        System.out.println("Lado:");
        return 5 * readPositiveDouble(input);
    }

    private static double readPositiveDouble(Scanner input) {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(input.nextLine().trim());
                if (value > 0)
                    return value;
                System.out.println("El valor debe ser mayor que 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número válido.");
            }
        }
    }
}
