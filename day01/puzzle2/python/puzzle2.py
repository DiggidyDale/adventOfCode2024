import re


def main():
    file = "resources/input.txt"
    list_of_locations = get_locations(file)
    analysed_right = analyse_right(list_of_locations[1])
    similarity_score = get_similarity_score(list_of_locations[0], analysed_right)
    print("Similarity score: " + str(similarity_score))


def get_locations(file):
    left_list = []
    right_list = []
    content = []
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


def analyse_right(locations):
    result = {}
    for loc in locations:
        if loc not in result:
            result[loc] = 1
        else:
            result[loc] += 1

    return result


def get_similarity_score(left, right):
    total = 0
    for loc in left:
        total += right.get(loc, 0) * loc

    return total


if __name__ == "__main__":
    main()
