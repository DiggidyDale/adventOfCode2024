package main

import (
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	const file = "resources/input.txt"
	var listOfLocations = getLocations(file)
	var analysedRight = analyseList(listOfLocations[1])
	var similarityScore = getSimilarityScore(listOfLocations[0], analysedRight)

	fmt.Println("Similarity Score is: ", similarityScore)
}

func getSimilarityScore(leftList []int64, right map[int64]int64) int64 {
	var total int64 = 0
	for _, left := range leftList {
		value, exists := right[left]
		if exists {
			total += (value * left)
		}
	}
	return total
}

func analyseList(locations []int64) map[int64]int64 {
	result := make(map[int64]int64, len(locations))
	for _, location := range locations {
		loc, exists := result[location]
		if !exists {
			result[location] = 1
		} else {
			result[location] = loc + 1
		}
	}
	return result
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
