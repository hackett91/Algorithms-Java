class SortedLL:

    def __init__(self):
        self.dummy = Node(None, None)
        self.dummy.next = self.dummy
        self.head = Node(self.dummy, 'Start')

    def print_nodes(self):
        current = self.head

        while current != self.dummy:
            print(current.data)
            current = current.next


    def insert(self, value):
        new_node = Node(None, value)
        current = self.head

        while (current.next != self.dummy and value > current.next.data):
            current = current.next

        new_node.next = current.next
        current.next = new_node


    def remove(self, value):

        if self.isEmpty():
            print("List is Empty, you dummy!")
            return

        current = self.head

        while (current.next.data != value and current.next != self.dummy):
            current = current.next

        # If we found the value
        if current.next.data == value:
            current.next = current.next.next

        else:
            print("\nValue not in the list, you dummy!\n")




    def isEmpty(self):
        current = self.head

        if current.next == self.dummy:
            return True
        else:
            return False


class Node:
    def __init__(self, node, data):
        self.data = data
        self.next = node



sorted_list = SortedLL()

sorted_list.insert(1)
sorted_list.insert(4)
sorted_list.insert(2)
sorted_list.insert(6)
sorted_list.insert(5)

sorted_list.print_nodes()
print("\n")

sorted_list.remove(4)
sorted_list.print_nodes()
