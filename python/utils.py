import os
import time
import networkx as nx
from rules import oneCharDiff, anagrams, addOneChar, removeOneChar

MAX_WORD_LENGTH = 9
DICTIONARY_PATH = "./words.italian.txt"
ADJ_LIST_PATH = "graph.adjlist"


def read_file(path):
    with open(path, "r") as f:
        lines = f.read().split()
    return lines


def apply_rules(word, words):
    nearby_list = oneCharDiff(
        word, words) + addOneChar(word, words) + removeOneChar(word, words)
    if len(word) < MAX_WORD_LENGTH:
        nearby_list += anagrams(word, words)
    return nearby_list


def nearbyWordsList(word):
    words = set(read_file(DICTIONARY_PATH))
    nearby_list = list()
    if word not in words:
        nearby_list = apply_rules(word, words)
    return nearby_list


def addWord(G, word):
    if word not in G:
        for nearby_word in nearbyWordsList(word):
            G.add_edge(word, nearby_word)
    return G


def buildGraph():
    dictionary = read_file(DICTIONARY_PATH)
    words = set(read_file(DICTIONARY_PATH))

    G = nx.Graph()
    start_time = time.time()
    print("Building graph...")
    for word in dictionary:
        G.add_node(word)
        for nearby_word in apply_rules(word, words):
            G.add_edge(word, nearby_word)
    end_time = time.time()
    print("Graph built in {} seconds".format(end_time - start_time))

    nx.write_adjlist(G, "graph.adjlist")
    return G


def start(start_word, end_word):
    if os.path.isfile(ADJ_LIST_PATH):
        G = nx.read_adjlist(ADJ_LIST_PATH)
    else:
        G = buildGraph()

    G = addWord(G, start_word)
    G = addWord(G, end_word)

    try:
        start_time = time.time()
        path = nx.shortest_path(G, start_word, end_word)
        end_time = time.time()
        print("Path found in {} seconds".format(end_time - start_time))
        print("Path length: {}".format(len(path)))
        print("Path: {}".format(path))
    except nx.NetworkXException:
        print("No path between {0} and {1}".format(start_word, end_word))
