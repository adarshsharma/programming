#include <iostream>
#include <string>


void solve(int A) {
    int len = A / 3;
    if (A % 3 != 0) {
        len += 1;
    }

    len = len > 3 ? len: 3;

    int cell[1000][1000] = {{0}};

    int i = 1;
    int j = 1;

    // check first 2 column
    while (cell[0][0] + cell[1][0] + cell[2][0] < 3 || cell[0][1] + cell[1][1] + cell[2][1] < 3) {
        std::cout << 2;
        std::cout << " ";
        std::cout << 2;
        std::cout << std::endl;
        std::cin >> i >> j;
        cell[i - 1][j - 1] = 1;
        if (i <= 0 && j <= 0) {
            break;
        }
    }

    for (int c = 2; c < len && i > 0 && j > 0; c++) {
        while (cell[0][c] + cell[1][c] + cell[2][c] < 3 && i > 0 && j > 0) {
            std::cout << 2;
            std::cout << " ";
            std::cout << c;
            std::cout << std::endl;
            std::cin >> i >> j;
            cell[i - 1][j - 1] = 1;
        }
    }
}

int main() {
  int num_test_cases;
  std::cin >> num_test_cases;
  for (int i = 0; i < num_test_cases; ++i) {
    int m;
    std::cin >> m;
    solve(m);
  }
  return 0;
}

