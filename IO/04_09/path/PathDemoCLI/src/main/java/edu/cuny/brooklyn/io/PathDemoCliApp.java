package edu.cuny.brooklyn.io;

import java.io.IOException;
import java.net.URI;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;

public class PathDemoCliApp {
	public static void main(String[] args) {
		System.out.format("The Working Directory is %s%n", System.getProperty("user.dir"));

		PathDemoCliApp demo = new PathDemoCliApp();

		System.out.println("Retrieve path information from an absolute path");
		demo.retrieveAbsPathInfo();

		System.out.println("--------------------------");

		System.out.println("Retrieve path information from a relative path");
		demo.retrieveRelPathInfo();

		System.out.println("--------------------------");

		System.out.println("Normalize a path (remove redundancy if any) from a path");
		demo.normalizePath();

		System.out.println("--------------------------");

		System.out.println("Convert an absolute path to URI");
		demo.convertAbsPathToUri();

		System.out.println("--------------------------");

		System.out.println("Convert a relative path to URI");
		demo.convertRelPathToUri();

		System.out.println("--------------------------");

		System.out.println("Convert a relative path to an absolute path");
		demo.convertToAbsolutePath();

		System.out.println("--------------------------");

		System.out.println("Convert a path to real path");
		demo.convertToRealPath();

		System.out.println("--------------------------");

		System.out.println("Join two paths");
		demo.joinTwoPaths();

		System.out.println("--------------------------");

		System.out.println("Create relative path of the two paths");
		demo.createRelativePath();

		System.out.println("--------------------------");

		System.out.println("Compare paths");
		demo.comparePaths();

		System.out.println("--------------------------");

		System.out.println("Sort paths");
		demo.sortPaths();

		System.out.println("--------------------------");

		System.out.println("Iterate a path");
		demo.iteratePath();
	}

	private void retrieveAbsPathInfo() {
		// On Microsoft Windows use:
		Path absPath = Paths.get("C:\\home\\alice\\hw1.txt");
		// The above is equivalent to
		// Path absPath = Paths.get("C:", "home", "alice", "hw1.txt");
		// On Unix-like OS (Mac OS X) use:
		// Path absPath = Paths.get("/home/alice/hw1.txt");
		// The above is equivalent to
		// Path absPath = Paths.get("/", "home", "alice", "hw1.txt");
		System.out.format("toString: %s%n", absPath.toString());
		System.out.format("getFileName: %s%n", absPath.getFileName());
		System.out.format("getName(0): %s%n", absPath.getName(0));
		System.out.format("getNameCount: %d%n", absPath.getNameCount());
		System.out.format("subpath(0,2): %s%n", absPath.subpath(0, 2));
		System.out.format("getParent: %s%n", absPath.getParent());
		System.out.format("getRoot: %s%n", absPath.getRoot());
	}

	private void retrieveRelPathInfo() {
		// On Microsoft Windows use:
		Path relPath = Paths.get("alice\\hw1.txt");
		// The above is equivalent to
		// Path relPath = Paths.get("alice", "hw1.txt");
		// On Unix-like OS (Mac OS X) use:
		// Path relPath = Paths.get("alice/hw1.txt");
		// The above is equivalent to
		// Path relPath = Paths.get("alice", "hw1.txt");
		System.out.format("toString: %s%n", relPath.toString());
		System.out.format("getFileName: %s%n", relPath.getFileName());
		System.out.format("getName(0): %s%n", relPath.getName(0));
		System.out.format("getNameCount: %d%n", relPath.getNameCount());
		System.out.format("subpath(0,2): %s%n", relPath.subpath(0, 2));
		System.out.format("getParent: %s%n", relPath.getParent());
		System.out.format("getRoot: %s%n", relPath.getRoot());

	}

	private void normalizePath() {
		Path path = Paths.get("alice\\..\\alice\\..\\alice\\.\\hw1.txt");
		Path normPath = path.normalize();

		System.out.format("toString: %s%n", normPath.toString());
		System.out.format("getFileName: %s%n", normPath.getFileName());
		System.out.format("getName(0): %s%n", normPath.getName(0));
		System.out.format("getNameCount: %d%n", normPath.getNameCount());
		System.out.format("subpath(0,2): %s%n", normPath.subpath(0, 2));
		System.out.format("getParent: %s%n", normPath.getParent());
		System.out.format("getRoot: %s%n", normPath.getRoot());
	}

	private void convertAbsPathToUri() {
		// On Windows use:
		Path path = Paths.get("C:\\home\\alice\\hw1.txt");
		// On Unix-like use:
		// Path p1 = Paths.get("/home/alice/hw1.txt");
		URI pathUri = path.toUri();
		if (pathUri instanceof URI) {
			System.out.format("The URI of %s is %s%n", path, pathUri);
		}
	}

	private void convertRelPathToUri() {
		// On Windows use:
		Path path = Paths.get("alice\\hw1.txt");
		// On Unix-like use:
		// Path p1 = Paths.get("/home/alice/hw1.txt");
		URI pathUri = path.toUri();
		if (pathUri instanceof URI) {
			System.out.format("The URI of %s is %s%n", path, pathUri);
		}
	}

	private void convertToAbsolutePath() {
		Path path = Paths.get("alice", "hw1.txt");
		System.out.format("The relative path is %s%n", path);
		Path fullPath = path.toAbsolutePath();
		System.out.format("The relative path of %s is actually at %s%n", path, fullPath);
	}

	private void convertToRealPath() {
		Path path = Paths.get("src", "main", "java", "edu", "cuny", "brooklyn", "cisc3120", "fileio", "PathDemo.java");
		try {
			Path realPath = path.toRealPath();
			System.out.format("The real path of %s is %s%n", path, realPath);
		} catch (NoSuchFileException e) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (IOException x) {
			System.err.format("%s%n", x);
		}
	}

	private void joinTwoPaths() {
		// Microsoft Windows
		Path path = Paths.get("C:\\home\\alice");
		Path pathToAdd = Paths.get("homework\\hw1.txt");
		// Unix-like
		// Path path = Paths.get("/home/alice");
		// Path pathToAdd = Paths.get("homework/hw1.txt")
		Path newPath = path.resolve(pathToAdd);
		System.out.format("%s and %s are joined to yield %s%n", path, pathToAdd, newPath);
	}

	private void createRelativePath() {

		Path alice = Paths.get("alice");
		Path bob = Paths.get("bob");

		// The new path is relative to the original path.
		Path bobRelativeToAlice = alice.relativize(bob);
		System.out.format("%s relative to %s is %s (you are at alice) %n", bob, alice, bobRelativeToAlice);

		// The new path is relative to the original path.
		Path aliceRelativeToBob = bob.relativize(alice);
		System.out.format("%s relative to %s is %s (you are at bob) %n", alice, bob, aliceRelativeToBob);
	}

	private void comparePaths() {
		Path path = Paths.get("home", "alice", "hw1.txt");
		Path otherPath = Paths.get("home", "bob", "hw1.txt");
		Path beginning = Paths.get("home");
		Path ending = Paths.get("hw1.txt");

		if (path.equals(otherPath)) {
			System.out.format("%s and %s are identidal.%n", path, otherPath);
		} else {
			System.out.format("%s and %s are different.%n", path, otherPath);
		}

		if (path.startsWith(beginning)) {
			System.out.format("%s starts with %s%n", path, beginning);
		}

		if (path.endsWith(ending)) {
			System.out.format("%s starts with %s%n", path, ending);
		}
	}

	private void sortPaths() {
		Path path = Paths.get("home", "alice", "hw1.txt");
		Path otherPath = Paths.get("home", "bob", "hw1.txt");

		ArrayList<Path> pathList = new ArrayList<Path>();
		pathList.add(otherPath);
		pathList.add(path);

		System.out.println("Before sorting:");
		for (Path p : pathList) {
			System.out.format("path: %s%n", p);
		}

		// ArrayList.sort is introduced in Java 8. You can sort
		// a list using the Collections class if the list is
		// already "Comparable" (since Java 1.2).
		// Collections.sort(pathList);

		// use ArrayList.sort and a Lambda expression
		pathList.sort((lhs, rhs) -> lhs.compareTo(rhs));

		// the above can be written as, if it helps you understand
		// pathList.sort((Path lhs, Path rhs) -> { return lhs.compareTo(rhs); });

		// Or use an anonymouse class
		// pathList.sort(new Comparator<Path>() {
		//
		// @Override
		// public int compare(Path lhs, Path rhs) {
		// return lhs.compareTo(rhs);
		// }
		// });

		// Or use a named class
		// final class PathComparator implements Comparator<Path> {
		//
		// @Override
		// public int compare(Path lhs, Path rhs) {
		// return lhs.compareTo(rhs);
		// }
		//
		// }
		// pathList.sort(new PathComparator());

		System.out.println("After sorting:");
		for (Path p : pathList) {
			System.out.format("path: %s%n", p);
		}

	}

	public void iteratePath() {
		Path path = Paths.get("home", "alice", "hw1.txt");
		System.out.format("%s has the following names: %n", path);
		for (Path part : path) {
			System.out.println(part);
		}
	}
}
