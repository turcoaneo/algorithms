package org.algorithms.test.copilot.patterns.structural;

import java.util.ArrayList;
import java.util.List;

// 1️⃣ Base Component Interface
abstract class FileSystemNode {
    protected String name;
    protected FileSystemNode parent; // Keeps track of hierarchy

    public FileSystemNode(String name) {
        this.name = name;
    }

    public void setParent(FileSystemNode parent) {
        this.parent = parent;
    }

    public abstract void showDetails(String indent);
}

// 2️⃣ Leaf Class: File
class File extends FileSystemNode {
    public File(String name) { super(name); }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📄 File: " + name);
    }
}

// 3️⃣ Composite Class: Folder (Tree Structure)
class Folder extends FileSystemNode {
    private final List<FileSystemNode> children = new ArrayList<>();

    public Folder(String name) { super(name); }

    public void addComponent(FileSystemNode node) {
        node.setParent(this); // Establish hierarchical connection
        children.add(node);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📂 Folder: " + name);
        for (FileSystemNode node : children) {
            node.showDetails(indent + "   "); // Indentation for hierarchy
        }
    }
}

// 4️⃣ Client Code: Creating a Proper Tree Structure
public class CompositePatternTreeDemo {
    public static void main(String[] args) {
        Folder root = new Folder("Root");

        Folder subFolder = new Folder("SubFolder");
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File subFile = new File("subfile1.txt");

        // Establish hierarchy
        root.addComponent(file1);
        root.addComponent(file2);
        root.addComponent(subFolder);
        subFolder.addComponent(subFile);

        root.showDetails(""); // Displays proper tree structure
    }
}
