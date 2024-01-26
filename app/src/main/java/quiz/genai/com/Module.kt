package quiz.genai.com

data class Module(
    val name: String,
    val rating: Float,
    val hoursRequired: Int,
    val contentType: ContentType,
    val details: String
)

enum class ContentType {
    VIDEO,
    ARTICLE
}

val androidDevelopmentModules = listOf(
    Module("Introduction to Android", 4.5f, 10, ContentType.VIDEO, "Covering basics of Android development"),
    Module("UI Design with XML", 4.2f, 12, ContentType.VIDEO, "Creating user interfaces using XML"),
    Module("Database and Storage", 4.7f, 15, ContentType.VIDEO, "Working with SQLite and data storage"),
    Module("Advanced Topics", 4.8f, 20, ContentType.ARTICLE, "Deep dive into advanced Android concepts")
)

val uiUxModules = listOf(
    Module("Fundamentals of UI/UX", 4.3f, 10, ContentType.VIDEO, "Introduction to UI/UX principles"),
    Module("Wireframing and Prototyping", 4.6f, 14, ContentType.VIDEO, "Creating wireframes and prototypes"),
    Module("User Testing", 4.4f, 12, ContentType.VIDEO, "Understanding the importance of user testing"),
    Module("Case Studies", 4.5f, 18, ContentType.ARTICLE, "Analyzing UI/UX case studies")
)

val marketingModules = listOf(
    Module("Digital Marketing Basics", 4.2f, 8, ContentType.VIDEO, "Introduction to digital marketing strategies"),
    Module("Social Media Marketing", 4.5f, 12, ContentType.VIDEO, "Utilizing social media for marketing"),
    Module("SEO and Analytics", 4.6f, 15, ContentType.VIDEO, "Optimizing content for search engines"),
    Module("Campaign Planning", 4.4f, 20, ContentType.ARTICLE, "Planning effective marketing campaigns")
)

val productDevelopmentModules = listOf(
    Module("Product Lifecycle", 4.7f, 10, ContentType.VIDEO, "Understanding the stages of product development"),
    Module("Market Research", 4.5f, 14, ContentType.VIDEO, "Conducting market research for product ideas"),
    Module("Agile Methodology", 4.8f, 12, ContentType.VIDEO, "Implementing Agile practices in development"),
    Module("Case Studies", 4.6f, 18, ContentType.ARTICLE, "Analyzing successful product development cases")
)


val UiUxArticle = "User Interface (UI) and User Experience (UX) are integral elements in design, defining the interaction between humans and digital interfaces. UI focuses on the visual and interactive aspects, ensuring an appealing and intuitive design. On the other hand, UX encompasses the overall experience, incorporating usability, accessibility, and user satisfaction.\n" +
        "\n" +
        "Effective UI/UX design considers the user's journey, from the first interaction to a seamless, enjoyable experience. It involves understanding user behavior, wireframing, prototyping, and continuous testing to refine the design. A successful UI/UX design enhances user engagement, increases user satisfaction, and fosters brand loyalty.\n" +
        "\n" +
        "In the ever-evolving digital landscape, UI/UX plays a pivotal role in shaping products and services. As technology advances, the importance of creating designs that prioritize user needs and preferences becomes increasingly evident, making UI/UX a critical aspect of modern design practices."