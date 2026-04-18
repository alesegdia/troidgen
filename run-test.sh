#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

tests=(
    "Test_LayoutBuilder"
    "Test_LayoutBuilder2"
    "Test_ManualGeometryProviderBasic"
    "Test_OverlapSolverManual"
    "Test_OverlapSolverRandom"
    "Test_OverlapSolverRandomBig"
    "Test_OverlapSolverRandomProvider"
    "Test_RectCollision"
    "Test_RectTouching"
    "Test_Restrictions"
)

echo ""
echo "Available troidgen tests:"
PS3="Select a test (1-${#tests[@]}): "

select chosen in "${tests[@]}"; do
    if [[ -n "$chosen" ]]; then
        echo ""
        echo "Running $chosen ..."
        ./gradlew "run_${chosen}"
        break
    else
        echo "Invalid selection, please try again."
    fi
done
