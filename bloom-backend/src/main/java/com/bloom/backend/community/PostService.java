package com.bloom.backend.community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private static final List<String> BANNED_WORDS = Arrays.asList("spam","hate","abuse","violence","harassment");
    private static final String[] ADJECTIVES = {"Purple","Golden","Silver","Crimson","Azure","Jade","Coral","Amber","Violet","Scarlet"};
    private static final String[] NOUNS = {"Orchid","Rose","Lily","Daisy","Iris","Lotus","Tulip","Dahlia","Jasmine","Poppy"};
    private String generatePseudonym() { Random random = new Random(); String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)]; String noun = NOUNS[random.nextInt(NOUNS.length)]; int number = random.nextInt(90) + 10; return adjective + noun + number; }
    private void moderateContent(String content) { if (content == null) return; String lower = content.toLowerCase(); for (String word : BANNED_WORDS) { if (lower.contains(word)) { throw new RuntimeException("Post rejected: content violates community guidelines."); } } }
    public Post createPost(Post post) { moderateContent(post.getContent()); post.setPseudonym(generatePseudonym()); return postRepository.save(post); }
    public List<Post> getAllPosts() { return postRepository.findAllByOrderByCreatedAtDesc(); }
    public List<Post> getPostsByCategory(String category) { return postRepository.findByCategoryOrderByCreatedAtDesc(category); }
    public void deletePost(Long id) { postRepository.deleteById(id); }
    public Post likePost(Long id) { Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found")); post.setLikes(post.getLikes() + 1); return postRepository.save(post); }
}