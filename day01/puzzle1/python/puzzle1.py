import re


def main():
    file = "resources/input.txt"
    list_of_locations = get_locations(file)
    sorted_list_of_locations = get_sorted_locations(list_of_locations)
    total_distance = get_total_distance(sorted_list_of_locations)
    print("Total distance: " + str(total_distance))


def get_locations(file):
    left_list = []
    right_list = []
    try:
        with open(file, "r") as file:
            content = file.readlines()
    except FileNotFoundError:
        print("File not found!")
    for line in content:
        split = re.split(r'\s+', line)
        left_list.append(int(split[0]))
        right_list.append(int(split[1]))
    
    return [left_list, right_list]


def get_sorted_locations(list_of_locations):
    left_list = list_of_locations[0]
    right_list = list_of_locations[1]

    left_list.sort()
    right_list.sort()

    return [left_list, right_list]


def get_total_distance(sorted_locations):
    left_list = sorted_locations[0]
    right_list = sorted_locations[1]
    total = 0
    for i, value in enumerate(left_list):
        total += abs(value - right_list[i])

    return total


if __name__ == "__main__":
    main()
