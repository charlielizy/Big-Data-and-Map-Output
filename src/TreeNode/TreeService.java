package TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class TreeService {

	private List<TreeNode> tree;

	public TreeService(List<TreeNode> tree) {
		this.tree = tree;
	}

	public TreeNode getRoot() {
		Optional<TreeNode> rootOption = this.tree.stream().filter(s -> s.getParentIndex() == 0).findAny();
		if (rootOption.isPresent()) {
			return rootOption.get();
		} else {
			return null;
		}
	}

	/**
	 * Recursively looking up the character which mapping to the index
	 * 
	 * @param stack: with root of the tree in it
	 * @param index: the index that need to lookup up from the tree
	 * @return the character of
	 */
	private String lookingUpTreeRecursive(Stack<TreeNode> stack, int index) {
		if (null == stack || stack.isEmpty()) {
			return null;
		}
		if (stack.peek().getIndex() == index) {
			return stack.peek().getCharactor();
		}
		List<TreeNode> sonNodes = this.tree.stream().filter(s -> s.getParentIndex() == stack.peek().getIndex())
				.collect(Collectors.toList());
		if (null == sonNodes || sonNodes.isEmpty()) {
			this.tree.remove(stack.peek());
			stack.pop();
		} else {
			for (TreeNode node : sonNodes) {
				stack.add(node);
			}
		}
		return lookingUpTreeRecursive(stack, index);
	}

	/**
	 * input index, get the root of the tree and put it in a stack, then call lookup
	 * method
	 * 
	 * @param index
	 * @return
	 */
	public String getCharactorByIndex(int index) {
		if (null == this.tree || this.tree.isEmpty()) {
			return null;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.add(this.getRoot());
		return lookingUpTreeRecursive(stack, index);
	}

	/**
	 * @param userEnter
	 * @return
	 */
	public Integer validateUserEnter(String userEnter) {
		List<Integer> indexList = this.tree.stream()
				.collect(Collectors.mapping(TreeNode::getIndex, Collectors.toList()));
		int index = -1;
		try {
			index = Integer.valueOf(userEnter);
		} catch (NumberFormatException ex) {
			return -1;
		}
		return indexList.contains(index) ? index : -1;
	}

	public static void main(String[] args) {
		String userEnter = null;
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the index (1-13), type 'exit' to stop running.");
		while (!(userEnter = input.next()).equalsIgnoreCase("exit")) {
			System.out.print("'" + userEnter + "' entered ");
			List<TreeNode> treeStructure = new ArrayList<TreeNode>(Arrays.asList(
				new TreeNode(1, "R", 0), new TreeNode(4, "G", 1), new TreeNode(2, "F", 1), 
				new TreeNode(8, "Q", 1), new TreeNode(6, "H", 4),new TreeNode(10, "E", 4), 
				new TreeNode(5, "Z", 2), new TreeNode(7, "P", 2),new TreeNode(11, "L", 2), 
				new TreeNode(9, "U", 8), new TreeNode(3, "X", 5),new TreeNode(12, "T", 9), 
				new TreeNode(13, "D", 9)));

			TreeService treeService = new TreeService(treeStructure);
			int index = treeService.validateUserEnter(userEnter);
			if (index > 0) {
				String charactor = treeService.getCharactorByIndex(index);
				System.out.println(", Charactor is: '" + charactor + "'");
			} else {
				System.out.println("Not a number or not in 1-13. ");
			}
			System.out.println("Please enter the index (1-13), 'exit' to stop running.");
		}
		input.close();
		System.out.println("Exit the programe. Thanks~");
	}
}