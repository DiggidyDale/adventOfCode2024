package main

import (
	"fmt"
	"math"
	"os"
	"slices"
	"strconv"
	"strings"
)

func main() {
	const file = "resources/input.txt"
	var listOfLocations = getLocations(file)
	var sortedLocations = getSortedLocations(listOfLocations)
	var totalDistance = getTotalDistance(sortedLocations)

	fmt.Println("Total Distance:", totalDistance)
}

func getTotalDistance(locations [][]int64) int64 {
	var leftList = locations[0]
	var rightList = locations[1]
	if len(leftList) != len(rightList) {
		fmt.Println("The length of the left locations does not match the length of the right locations")
		return 0
	}
	var totalDistance int64 = 0
	for i := 0; i < len(leftList); i++ {
		totalDistance += int64(math.Abs(float64(rightList[i] - leftList[i])))
	}
	return totalDistance
}

func getSortedLocations(locations [][]int64) [][]int64 {
	slices.Sort(locations[0])
	slices.Sort(locations[1])
	return locations
}

func getLocations(file string) [][]int64 {
	bytes, err := os.ReadFile(file)
	if err != nil {
		fmt.Println("Error reading file", err)
		return nil
	}
	output := string(bytes)
	lines := strings.Split(output, "\n")
	var locations = make([][]int64, 2)
	for _, line := range lines {
		fields := strings.Fields(line)
		left, err := strconv.ParseInt(fields[0], 10, 64)
		if err != nil {
			fmt.Println("Error parsing left value", err)
			return nil
		}
		right, err := strconv.ParseInt(fields[1], 10, 64)
		if err != nil {
			fmt.Println("Error parsing right value", err)
			return nil
		}
		locations[0] = append(locations[0], left)
		locations[1] = append(locations[1], right)
	}

	return locations
}
