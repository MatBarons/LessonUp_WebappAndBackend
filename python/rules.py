from itertools import permutations

ALPHABET = "abcdefghijklmnopqrstuvwxyz"

def oneCharDiff(start_word, words_list):
    words_one_char_diff = list()
    for i in range(len(start_word)):
        start_word_list = list(start_word)
        for char in ALPHABET:
            start_word_list[i] = char
            word = "".join(start_word_list)
            if (word in words_list) & (word != start_word):
                if word not in words_one_char_diff:
                    words_one_char_diff.append(word)
    return words_one_char_diff

def anagrams(start_word, words_list):
    words_anagrams = list()
    for permuted_word in permutations(start_word):
        word = "".join(permuted_word)
        if (word in words_list) & (word != start_word):
            if word not in words_anagrams:
                words_anagrams.append(word)
    return words_anagrams

def addOneChar(start_word, words_list):
    words_add_one_diff = list()
    for i in range(len(start_word) + 1):
        start_word_list = list(start_word)
        for char in ALPHABET:
            start_word_list.insert(i, char)
            word = "".join(start_word_list)
            if (word in words_list) & (word != start_word):
                if word not in words_add_one_diff:
                    words_add_one_diff.append(word)
            start_word_list.pop(i)
    return words_add_one_diff

def removeOneChar(start_word, words_list):
    words_remove_one_diff = list()
    for i in range(len(start_word)):
        start_word_list = list(start_word)
        char = start_word_list.pop(i)
        word = "".join(start_word_list)
        if (word in words_list) & (word != start_word):
            if word not in words_remove_one_diff:
                words_remove_one_diff.append(word)
        start_word_list.insert(i, char)
    return words_remove_one_diff
