#include <bits/stdc++.h>
using namespace std;

int min_moves(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;

    vector<int> dp(n+1, 0);
    dp[1] = 1;
    dp[2] = 2;

    cout << "starting from: " << 1 << " last steps: " << dp[1] << endl;
    cout << "starting from: " << 2 << " last steps: " << dp[2] << endl;

    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i-1] + 2*dp[i-2] + 1;
        cout << "starting from: " << i << " last steps: " << dp[i] << endl;
    }

    return dp[n];
}

int main() {
    int n = 6;
    int moves = min_moves(n);
    cout << "The minimum number of moves to turn off all switches is: " << moves << endl;
    return 0;
}
