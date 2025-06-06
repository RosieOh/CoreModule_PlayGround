// Theme toggle functionality
const themeToggle = document.getElementById('themeToggle');
const prefersDarkScheme = window.matchMedia('(prefers-color-scheme: dark)');

function toggleTheme() {
    const currentTheme = document.documentElement.getAttribute('data-theme');
    const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-theme', newTheme);
    localStorage.setItem('theme', newTheme);
    // Update icon and text
    if (themeToggle) {
        const icon = themeToggle.querySelector('i');
        const text = themeToggle.querySelector('span');
        icon.className = newTheme === 'dark' ? 'fas fa-sun' : 'fas fa-moon';
        text.textContent = newTheme === 'dark' ? 'Light Mode' : 'Dark Mode';
    }
    document.body.classList.toggle('dark-theme', newTheme === 'dark');
}

function initializeTheme() {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
        document.documentElement.setAttribute('data-theme', savedTheme);
        document.body.classList.toggle('dark-theme', savedTheme === 'dark');
        if (themeToggle) {
            const icon = themeToggle.querySelector('i');
            const text = themeToggle.querySelector('span');
            icon.className = savedTheme === 'dark' ? 'fas fa-sun' : 'fas fa-moon';
            text.textContent = savedTheme === 'dark' ? 'Light Mode' : 'Dark Mode';
        }
    } else if (prefersDarkScheme.matches) {
        document.documentElement.setAttribute('data-theme', 'dark');
        document.body.classList.add('dark-theme');
        if (themeToggle) {
            const icon = themeToggle.querySelector('i');
            const text = themeToggle.querySelector('span');
            icon.className = 'fas fa-sun';
            text.textContent = 'Light Mode';
        }
    }
}

document.addEventListener('DOMContentLoaded', initializeTheme);
if (themeToggle) themeToggle.addEventListener('click', toggleTheme);

// Font size toggle functionality
const fontSizeToggle = document.getElementById('fontSizeToggle');
let currentFontSize = 1;
if (fontSizeToggle) {
    fontSizeToggle.addEventListener('click', () => {
        currentFontSize = currentFontSize === 1 ? 1.2 : 1;
        document.body.style.fontSize = `${currentFontSize}rem`;
        localStorage.setItem('fontSize', currentFontSize);
    });
}
const savedFontSize = localStorage.getItem('fontSize');
if (savedFontSize) {
    currentFontSize = parseFloat(savedFontSize);
    document.body.style.fontSize = `${currentFontSize}rem`;
}

// High contrast toggle functionality
const highContrastToggle = document.getElementById('highContrastToggle');
let isHighContrast = false;
if (highContrastToggle) {
    highContrastToggle.addEventListener('click', () => {
        isHighContrast = !isHighContrast;
        document.documentElement.setAttribute('data-contrast', isHighContrast ? 'high' : 'normal');
        localStorage.setItem('highContrast', isHighContrast);
    });
}
const savedHighContrast = localStorage.getItem('highContrast');
if (savedHighContrast) {
    isHighContrast = savedHighContrast === 'true';
    document.documentElement.setAttribute('data-contrast', isHighContrast ? 'high' : 'normal');
}

// Search functionality (for list.html)
const moduleSearch = document.getElementById('moduleSearch');
if (moduleSearch) {
    moduleSearch.addEventListener('keyup', function() {
        const value = this.value.toLowerCase();
        document.querySelectorAll('.module-card').forEach(function(card) {
            card.style.display = card.textContent.toLowerCase().includes(value) ? '' : 'none';
        });
    });
}
// Filter functionality (for list.html)
const moduleFilter = document.getElementById('moduleFilter');
if (moduleFilter) {
    moduleFilter.addEventListener('change', function() {
        const value = this.value.toLowerCase();
        document.querySelectorAll('.module-card').forEach(function(card) {
            if (value === 'all') {
                card.style.display = '';
            } else {
                const title = card.querySelector('.card-title');
                card.style.display = title && title.textContent.toLowerCase().includes(value) ? '' : 'none';
            }
        });
    });
}
// Learning progress (for detail.html)
const progressBar = document.querySelector('.progress-bar');
const steps = document.querySelectorAll('.step');
let progress = 0;
function updateProgress() {
    if (!progressBar || !steps.length) return;
    progress += 25;
    progressBar.style.width = `${progress}%`;
    progressBar.setAttribute('aria-valuenow', progress);
    progressBar.textContent = `${progress}%`;
    if (progress <= 100) {
        steps[progress / 25 - 1].classList.add('completed');
        steps[progress / 25 - 1].querySelector('i').className = 'fas fa-check-circle';
    }
}
// Example run functionality (for detail.html)
function runExample() {
    const codeBlock = document.querySelector('.code-block code');
    if (!codeBlock) return;
    const code = codeBlock.textContent;
    // Here you would typically send the code to a backend service for execution
    console.log('Running example:', code);
    alert('Example executed successfully!');
} 